package com.agileboot.admin.customize.service.login.dto;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class CaptchaDTO {

    private Boolean isCaptchaOn;
    private String captchaCodeKey;
    private String captchaCodeImg;

}
