package com.illdangag.iam.service.impl;

import com.illdangag.iam.data.entity.Application;
import com.illdangag.iam.data.request.ApplicationCreate;
import com.illdangag.iam.exception.IamErrorCode;
import com.illdangag.iam.exception.IamException;
import com.illdangag.iam.repository.ApplicationRepository;
import com.illdangag.iam.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Optional;

@Validated
@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {
    final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application createApplication(ApplicationCreate applicationCreate) {
        String name = applicationCreate.getName();
        String description = applicationCreate.getDescription();

        Optional<Application> applicationOptional = this.applicationRepository.getApplicationByName(name);
        if (applicationOptional.isPresent()) { // 동일한 이름의 application이 존재하는 경우
            throw new IamException(IamErrorCode.DUPLICATE_APPLICATION_NAME);
        }

        Application newApplication = Application.builder()
                .name(name)
                .description(description)
                .build();

        this.applicationRepository.save(newApplication);

        return newApplication;
    }
}
