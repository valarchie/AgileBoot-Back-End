package com.agileboot.infrastructure.interceptor.repeatSubmit;

import java.util.Objects;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class RepeatRequest {

    private String repeatParams;
    private Long repeatTime;

    public boolean compareParams(RepeatRequest preRequest) {
        if (preRequest == null) {
            return false;
        }
        return Objects.equals(this.repeatParams, preRequest.repeatParams);
    }


    public boolean compareTime(RepeatRequest preRequest, int interval) {
        if (preRequest == null) {
            return false;
        }
        return preRequest.getRepeatTime() - this.repeatTime < interval;
    }

}
