package com.illdangag.iam.service;

import com.illdangag.iam.data.entity.Application;
import com.illdangag.iam.data.request.ApplicationCreate;

import javax.validation.Valid;

public interface ApplicationService {
    Application createApplication(@Valid ApplicationCreate applicationCreate);
}
