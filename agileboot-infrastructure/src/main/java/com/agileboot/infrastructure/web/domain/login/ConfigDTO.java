package com.agileboot.infrastructure.web.domain.login;

import com.agileboot.orm.common.result.DictionaryData;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class ConfigDTO {

    private Boolean isCaptchaOn;

    private Map<String, List<DictionaryData>> dictTypes;

}
