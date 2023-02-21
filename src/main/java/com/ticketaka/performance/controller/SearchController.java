package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequestMapping("/performance")
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<BaseResponse> getPrfByKeyword(
            @RequestParam(name = "keyword") String keyword,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) throws Exception {

        Slice<PerformanceInfo> data = searchService.getPerformanceSliceByKeyword(keyword,pageable);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }

    @GetMapping("/cat")
    public BaseResponse getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) throws Exception {

        Slice<PerformanceInfo> data = searchService.getPerformanceSliceByGenre(genre,pageable);

        return new BaseResponse(StatusCode.OK,data);
    }
}
