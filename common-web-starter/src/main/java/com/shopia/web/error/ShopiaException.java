package com.shopia.web.error;

import com.shopia.contracts.error.ErrorCode;

public class ShopiaException extends RuntimeException {
    private final ErrorCode errorCode;

    public ShopiaException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
