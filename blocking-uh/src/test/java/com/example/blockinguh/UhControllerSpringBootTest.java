package com.example.blockinguh;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UhControllerSpringBootTest {

    @MockBean
    private UhService service;

    private MockMvc mvc;

    @BeforeEach
    void setUp(final WebApplicationContext wac) {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getApplePen() throws Exception {
        given(service.apple()).willReturn("Apple");
        given(service.pen()).willReturn("Pen");

        mvc.perform(get("/uh"))
                .andExpect(content().string(containsString("ApplePen")));
    }
}
