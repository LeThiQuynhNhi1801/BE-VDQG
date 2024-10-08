package com.example.football.exception;


import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private String message;
    private String code;
    private int status;
    private Map<String, String> params;

    public BaseException() {
        this.status = 0;
        this.code = "";
        this.message = "";
        this.params = new HashMap<>();
    }

    public BaseException(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static void handleException(HttpServletResponse response, Exception ex) {
    }

    public void addParam(String key, String value) {
        if (Objects.isNull(params)) {
            params = new HashMap<>();
        }
        params.put(key, value);
    }

    public String getMes() {
        return message;
    }

}