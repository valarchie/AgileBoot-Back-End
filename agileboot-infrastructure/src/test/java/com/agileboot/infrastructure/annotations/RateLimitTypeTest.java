package com.agileboot.infrastructure.annotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.agileboot.infrastructure.annotations.RateLimit.LimitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RateLimitTypeTest {

    @Test
    public void testCombinedKey() {
        RateLimit mockLimit = mock(RateLimit.class);
        when(mockLimit.key()).thenReturn("Test");

        String combinedKey = LimitType.GLOBAL.generateCombinedKey(mockLimit);

        Assertions.assertEquals("Test-GLOBAL", combinedKey);
    }

}
