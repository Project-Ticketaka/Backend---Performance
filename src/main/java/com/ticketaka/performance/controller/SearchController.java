package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import com.ticketaka.performance.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequestMapping("/performance")
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<BaseResponse> getPrfByKeyword(
            @RequestParam(name = "keyword") String keyword,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {

        Slice<PerformanceInfo> data = searchService.getPerformanceSliceByKeyword(keyword,pageable);
        if(data.isEmpty()) {
            return ResponseEntity.ok(new BaseResponse(StatusCode.NO_CONTENT_FOUND));
        }

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }

    @GetMapping("/cat")
    public ResponseEntity<BaseResponse> getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {

        Slice<PerformanceInfo> data = searchService.getPerformanceSliceByGenre(genre,pageable);

        if(data.isEmpty()) {
            return ResponseEntity.ok(new BaseResponse(StatusCode.NO_CONTENT_FOUND));
        }

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }
}
