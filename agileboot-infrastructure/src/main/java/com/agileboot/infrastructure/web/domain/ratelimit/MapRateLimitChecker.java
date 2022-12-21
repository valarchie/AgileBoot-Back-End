package com.agileboot.infrastructure.web.domain.ratelimit;

import cn.hutool.cache.impl.LRUCache;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.annotations.RateLimit;
import com.google.common.util.concurrent.RateLimiter;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@SuppressWarnings("UnstableApiUsage")
@Component
@RequiredArgsConstructor
@Slf4j
public class MapRateLimitChecker extends AbstractRateLimitChecker{

    /**
     * 最大仅支持4096个key   超出这个key  限流将可能失效
     */
    private final LRUCache<String, RateLimiter> cache = new LRUCache<>(4096);


    @Override
    public void check(RateLimit rateLimit) {
        String combinedKey = rateLimit.limitType().generateCombinedKey(rateLimit);

        RateLimiter rateLimiter = cache.get(combinedKey,
            () -> RateLimiter.create((double) rateLimit.maxCount() / rateLimit.time()));

        if (!rateLimiter.tryAcquire()) {
            throw new ApiException(ErrorCode.Client.COMMON_REQUEST_TOO_OFTEN);
        }

        log.info("限制请求key:{}, combined key:{}", rateLimit.key(), combinedKey);
    }


    @Data
    static class RateLimitCacheDTO {

        private RateLimit rateLimitAnno;

        public RateLimitCacheDTO(RateLimit rateLimitAnno) {
            this.rateLimitAnno = rateLimitAnno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RateLimitCacheDTO that = (RateLimitCacheDTO) o;
            String combinedKeyThat = that.combinedKey();
            String combinedKeyThis = this.combinedKey();

            return Objects.equals(combinedKeyThis, combinedKeyThat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(combinedKey());
        }


        public String combinedKey() {
            return this.rateLimitAnno.limitType().generateCombinedKey(this.rateLimitAnno);
        }


    }

}
