package com.agileboot.infrastructure.annotations;

import com.agileboot.infrastructure.annotations.ratelimit.RateLimit;
import com.agileboot.infrastructure.annotations.ratelimit.RateLimit.LimitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RateLimitTypeTest {

    @Test
    void testCombinedKey() {
        RateLimit mockLimit = mock(RateLimit.class);
        when(mockLimit.key()).thenReturn("Test");

        String combinedKey = LimitType.GLOBAL.generateCombinedKey(mockLimit);

        Assertions.assertEquals("Test-GLOBAL", combinedKey);
    }

}
