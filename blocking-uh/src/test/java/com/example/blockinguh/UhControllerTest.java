package com.example.blockinguh;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UhControllerTest {

    @Test
    void getApplePen() {
        final UhService service = Mockito.mock(UhService.class);

        given(service.apple()).willReturn("Apple");
        given(service.pen()).willReturn("Pen");

        final UhController controller = new UhController(service);

        final String applePen = controller.getApplePen();

        assertTrue(applePen.contains("ApplePen"));
    }
}
