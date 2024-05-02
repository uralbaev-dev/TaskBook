package com.example.taskbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    public static int SUCCESS = 200;
    public static int NOT_FOUND = 404;

    @Getter
    public T data;
    public int code;
    public String message;

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS, "OK", data);
    }

    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> success(int code, T data) {
        return new ApiResponse<>(code, null, data);
    }

    public static <T> ApiResponse<T> success(String message, int code) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(SUCCESS, message, null);
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return new ApiResponse<>(code, message, null);
    }


    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }


    public boolean hasData() {
        return this.data != null;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
