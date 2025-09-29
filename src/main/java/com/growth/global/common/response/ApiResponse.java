package com.growth.global.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ApiResponse<T> {

    public static final String EMPTY = "";

    private int statusCode;
    private String message;
    private T data;

    // 성공 응답 (데이터 생성시)
    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(201, message, data);
    }

    // 성공 응답 (데이터만)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "성공", data);
    }

    // 성공 응답 (메시지만)
    public static ApiResponse<String> success(String message) {
        return new ApiResponse<>(200, message, EMPTY);
    }

    // 성공 응답 (데이터 없을때)
    public static ApiResponse<String> successWithoutData(int statusCode, String message) {
        return new ApiResponse<>(statusCode, message, EMPTY);
    }

    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}