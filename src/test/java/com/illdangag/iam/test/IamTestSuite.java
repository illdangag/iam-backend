package com.illdangag.iam.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class IamTestSuite {
    protected String TEXT_10 = "0123456789";
    protected String TEXT_100 = TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10 + TEXT_10;
    protected String TEXT_1000 = TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100 + TEXT_100;
}
