package com.ticketaka.performance.dto;

import lombok.Getter;

@Getter
public enum StatusCode {
    // 성공
    OK(200,"성공"),
    // 검색 결과가 DB에 존재하지 않을 때
    NO_DATA_SEARCHED(202,"검색어에 해당하는 공연을 찾을 수 없습니다."),

    // DB에 연결할 수 없을 때
    DB_UNABLE_TO_CONNECT(500, "데이터 베이스에 연결할 수 없습니다."),

    REDIS_TROUBLE_OCCURRED(500,"레디스 문제 발생"),

    NO_VACANCY(202,"예약 가능한 좌석이 없습니다."),

    NOT_ABLE_TO_CREATE(400, "예약을 완료할 수 없습니다."),

    RESERVATION_FAILED(500,"예약생성이 실패하였습니다."),

    NO_SUCH_ELEMENT(400, "조회한 데이터를 찾을 수 없습니다.");

    private final int code;
    private final String description;
    StatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
