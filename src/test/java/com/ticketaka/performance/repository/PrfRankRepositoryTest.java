package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.domain.PrfRank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PrfRankRepositoryTest {
    @Autowired
    private PrfRankRepository prfRankRepository;
    @Test
    void 랭킹_검색() {
        // given
        LocalDate baseDate = LocalDate.now();

        // when
        List<PrfRank> list = prfRankRepository.findByBaseDate(baseDate);

        // then
        assertEquals(0,list.size());
    }
}