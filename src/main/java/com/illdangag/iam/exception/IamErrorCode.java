package com.illdangag.iam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IamErrorCode implements IamError {
    // common: 0000XXXX

    // application: 0100XXXX
    NOT_EXIST_APPLICATION("01000001", 404, "Invalid authorization."),
    DUPLICATE_APPLICATION_NAME("01000002", 401, "Duplicate application name."),
    ;
    private final String code;
    private final int httpStatusCode;
    private final String message;
    @Override
    public String toString() {
        return "[" + this.code + "](" + this.httpStatusCode + ") " + this.message;
    }
}
