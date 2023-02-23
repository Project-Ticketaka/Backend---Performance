package com.ticketaka.performance.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.ticketaka.performance.utils")
public class FeignConfig {

}
