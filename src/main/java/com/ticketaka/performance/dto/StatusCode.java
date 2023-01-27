package com.ticketaka.performance.dto;

import lombok.Getter;

@Getter
public enum StatusCode {
    // 성공
    OK(200,"성공"),
    // 검색 결과가 DB에 존재하지 않을 때
    NO_CONTENT_FOUND(204,"검색어에 해당하는 공연을 찾을 수 없습니다."),

    // DB에 연결할 수 없을 때
    DB_UNABLE_TO_CONNECT(500, "데이터 베이스에 연결할 수 없습니다.");

    private final int code;
    private final String message;
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
