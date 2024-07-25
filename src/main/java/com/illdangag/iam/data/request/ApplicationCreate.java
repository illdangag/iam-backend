package com.illdangag.iam.data.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@Builder
public class ApplicationCreate {
    @Size(min =1, max = 100, message = "Name must be at least 1 character and less than 100 characters.")
    @Builder.Default
    private String name = "";

    @Size(max = 1000, message = "Description must be less then 100 characters.")
    @Builder.Default
    private String description = "";
}
