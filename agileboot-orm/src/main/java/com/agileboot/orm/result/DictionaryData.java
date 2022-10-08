package com.agileboot.orm.result;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.enums.interfaces.DictionaryEnum;
import lombok.Data;

/**
 * 字典模型类
 * @author valarchie
 */
@Data
public class DictionaryData {

    private String label;
    private String value;
    private String cssTag;

    @SuppressWarnings("rawtypes")
    public DictionaryData(DictionaryEnum enumType) {
        if (enumType != null) {
            this.label = enumType.description();
            this.value = StrUtil.toString(enumType.getValue());
            this.cssTag = enumType.cssTag();
        }
    }

}
