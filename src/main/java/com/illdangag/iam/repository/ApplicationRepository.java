package com.illdangag.iam.repository;

import com.illdangag.iam.data.entity.Application;

import java.util.Optional;

public interface ApplicationRepository {
    void save(Application application);
    Optional<Application> getApplication(Long id);
    Optional<Application> getApplicationByName(String name);
}
