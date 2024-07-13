package com.illdangag.iam.restdoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("restdoc: 서버 정보")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ServerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("기본 서버 정보")
    public void getServer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.branch").exists())
                .andExpect(jsonPath("$.commit").exists())
                .andExpect(jsonPath("$.tags").exists())
                .andDo(print())
                .andDo(document("SERVER_INFO",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("branch").description("git branch"),
                                        fieldWithPath("commit").description("git commit"),
                                        fieldWithPath("tags").description("git tags")
                                )
                        )

                );
    }
}
