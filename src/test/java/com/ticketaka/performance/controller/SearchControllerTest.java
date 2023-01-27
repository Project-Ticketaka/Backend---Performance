package com.ticketaka.performance.controller;

import com.ticketaka.performance.repository.PerformanceRepository;
import com.ticketaka.performance.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {
    @Autowired
    MockMvc mvc;
    @Test
    void 제목_검색() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("keyword","그림");
        map.add("page","0");
        map.add("size","10");
        map.add("sort","prfLoadedAt,DESC");
        mvc.perform(get("/performance/search").params(map))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 장르_검색() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("genre","서양음악(클래식)");
        map.add("page","0");
        map.add("size","10");
        map.add("sort","prfLoadedAt,DESC");
        mvc.perform(get("/performance/cat").params(map))
                .andExpect(status().isOk())
                .andDo(print());
    }
}