package com.example.pen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(PenApplication.class)
@TestPropertySource(properties = "sleep=0")
class PenApplicationTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp(final WebApplicationContext wac) {
        mvc = webAppContextSetup(wac).build();
    }

    @Test
    void pen() throws Exception {
        mvc.perform(get("/pen"))
                .andExpect(content().string("Pen"));
    }
}
