package com.agileboot.infrastructure.interceptor.repeatSubmit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.infrastructure.annotations.RepeatSubmit;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.filter.RepeatedlyRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 判断请求url和数据是否和上一次相同， 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 *
 * @author valarchie
 */
@Component
public class SameUrlDataInterceptorAbstract extends AbstractRepeatSubmitInterceptor {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    @Autowired
    private RedisCacheService redisCacheService;

    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        String nowParams = "";
        if (request instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
            nowParams = ServletUtil.getBody(repeatedlyRequest);
        }

        // body参数为空，获取Parameter的数据
        if (StrUtil.isEmpty(nowParams)) {
            // use jackson util to parse is more safe
            nowParams = JacksonUtil.to(request.getParameterMap());
        }

        RepeatRequest currentRequest = new RepeatRequest();
        currentRequest.setRepeatParams(nowParams);
        currentRequest.setRepeatTime(System.currentTimeMillis());

        // 请求地址（作为存放cache的key值）
        String url = request.getRequestURI();

        // 唯一值（没有消息头则使用请求地址）
        String submitKey = StrUtil.trimToEmpty(request.getHeader(header));

        // 唯一标识（指定key + url + 消息头）
//        String cacheRepeatKey = Constants.REPEAT_SUBMIT_KEY + url + submitKey;
        RepeatRequest preRequest = redisCacheService.repeatSubmitCache.getObjectById(url + submitKey);
        if (preRequest != null) {
            if (currentRequest.compareParams(preRequest) &&
                currentRequest.compareTime(preRequest, annotation.interval())) {
                return true;
            }
        }

        redisCacheService.repeatSubmitCache.set(url + submitKey, currentRequest);
        return false;
    }


}
