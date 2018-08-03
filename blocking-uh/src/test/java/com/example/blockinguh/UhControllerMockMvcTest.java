package com.example.blockinguh;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UhControllerMockMvcTest {

    private final UhService service = Mockito.mock(UhService.class);

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        final UhController controller = new UhController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getApplePen() throws Exception {
        given(service.apple()).willReturn("Apple");
        given(service.pen()).willReturn("Pen");

        mvc.perform(get("/uh"))
                .andExpect(content().string(containsString("ApplePen")));
    }
}
