package com.ticketaka.performance.config;

import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.repository.PrfSessionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.MapOptions;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.MapLoader;
import org.redisson.api.map.MapWriter;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableCaching
@NoArgsConstructor
public class RedisConfig {
    @Autowired
    private PrfSessionRepository prfSessionRepository;

    private @Value("${spring.redis.host}") String host;
    private @Value("${spring.redis.port}") int port;

    public RedisConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Bean("redisClient")
    public RedissonClient redissonClient() {
        Config redisConfig = new Config();
        redisConfig.useSingleServer()
                .setAddress("redis://"+host + ":" + port)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(10);
        return Redisson.create(redisConfig);
    }

    @Bean
    @DependsOn(value = "redisClient")
    public RMapCache<Integer, PrfSession> prfSessionRMapCache() throws Exception {
        return redissonClient().getMapCache("PrfSession", MapOptions.<Integer, PrfSession>defaults()
                .loader(getPrfSessionMapLoader())
                .writer(getPrfSessionMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
                .writeBehindBatchSize(100)
                .writeBehindDelay(10000)
        );
    }

    private MapWriter<Integer, PrfSession> getPrfSessionMapWriter() {
        return new MapWriter<Integer, PrfSession>() {
            @Override
            public void write(Map<Integer, PrfSession> map) {
                map.forEach((k,v) -> {
                    prfSessionRepository.save(v);
                });
            }

            @Override
            public void delete(Collection<Integer> keys) {
                keys.stream().forEach(key -> {
                    prfSessionRepository.deleteById(key);
                });
            }
        };
    }

    private MapLoader<Integer, PrfSession> getPrfSessionMapLoader() throws Exception{
        return new MapLoader<Integer, PrfSession>() {
            @Override
            public PrfSession load(Integer key) {
                return prfSessionRepository.findById(key).get();
            }

            @Override
            public Iterable<Integer> loadAllKeys() {
                return prfSessionRepository.findAllIds();
            }
        };
    }
}
