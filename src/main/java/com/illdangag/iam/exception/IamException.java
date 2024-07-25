package com.illdangag.iam.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illdangag.iam.data.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public class IamException extends RuntimeException {
    private IamError error;
    private String message;

    public IamException(IamError error) {
        super(error.toString());
        this.error = error;
    }

    public IamException(IamError error, String message) {
        this(error);
        this.message = message;
    }

    public IamException(IamError error, Throwable cause) {
        super(error.toString(), cause);
    }

    public IamException(IamError error, String message, Throwable cause) {
        this(error, cause);
        this.message = message;
    }

    public String getErrorCode() {
        return this.error.getCode();
    }

    public MediaType getHttpContentType() {
        return MediaType.APPLICATION_JSON;
    }

    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder()
                .code(this.error.getCode()).message(this.getMessage()).build();
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(getErrorResponse());
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
