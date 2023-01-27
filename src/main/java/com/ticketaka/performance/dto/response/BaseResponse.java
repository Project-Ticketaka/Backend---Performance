package com.ticketaka.performance.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ticketaka.performance.dto.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@JsonPropertyOrder({"code", "message", "data"})
public class BaseResponse<T> {
    private StatusCode code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(StatusCode code) {
        this.code = code;
    }
}
