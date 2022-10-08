package com.agileboot.infrastructure.web.domain.login;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class CaptchaDTO {

    private Boolean isCaptchaOn;
    private String uuid;
    private String img;

}
