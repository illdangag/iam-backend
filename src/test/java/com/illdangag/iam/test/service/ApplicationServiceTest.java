package com.illdangag.iam.test.service;

import com.illdangag.iam.data.request.ApplicationCreate;
import com.illdangag.iam.exception.IamException;
import com.illdangag.iam.service.ApplicationService;
import com.illdangag.iam.test.IamTestSuite;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

@DisplayName("service: application - 생성")
@Slf4j
@Transactional
public class ApplicationServiceTest extends IamTestSuite {
    @Autowired
    private ApplicationService applicationService;

    @Test
    @DisplayName("생성")
    public void createApplication() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("application name")
                .description("application description")
                .build();
        this.applicationService.createApplication(applicationCreate);
    }

    @Test
    @DisplayName("이름 중복")
    public void duplicateName() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("duplication name")
                .description("description")
                .build();
        this.applicationService.createApplication(applicationCreate);

        IamException exception = Assertions.assertThrows(IamException.class, () -> {
            this.applicationService.createApplication(applicationCreate);
        });
        Assertions.assertEquals(exception.getErrorCode(), "01000002");
    }

    @Test
    @DisplayName("이름에 빈 문자열")
    public void emptyStringName() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("")
                .description("")
                .build();

        Assertions.assertThrows(ValidationException.class, () -> {
            this.applicationService.createApplication(applicationCreate);
        });
    }

    @Test
    @DisplayName("이름에 100글자 문자열")
    public void maxStringName() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name(TEXT_100)
                .description("")
                .build();

        this.applicationService.createApplication(applicationCreate);
    }

    @Test
    @DisplayName("이름에 101글자 문자열")
    public void overflowStringName() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name(TEXT_100 + '1')
                .description("")
                .build();

        Assertions.assertThrows(ValidationException.class, () -> {
            this.applicationService.createApplication(applicationCreate);
        });
    }

    @Test
    @DisplayName("설명 없음")
    public void emptyDescription() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("empty description")
                .description("")
                .build();
        this.applicationService.createApplication(applicationCreate);
    }

    @Test
    @DisplayName("설명에 1000글자 문자열")
    public void maxDescription() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("max description")
                .description(TEXT_1000)
                .build();

        this.applicationService.createApplication(applicationCreate);
    }

    @Test
    @DisplayName("이름에 1001글자 문자열")
    public void overflowDescription() {
        ApplicationCreate applicationCreate = ApplicationCreate.builder()
                .name("overflow description")
                .description(TEXT_1000 + 1)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> {
            this.applicationService.createApplication(applicationCreate);
        });
    }
}
