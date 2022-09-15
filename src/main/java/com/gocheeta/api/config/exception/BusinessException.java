package com.gocheeta.api.config.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionId = 1L;
    private String errorCode;
    private String errorMessage;

    public BusinessException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException() {

    }
}

