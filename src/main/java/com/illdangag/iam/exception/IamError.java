package com.illdangag.iam.exception;

public interface IamError {
    String getCode();
    int getHttpStatusCode();
    String getMessage();
}
