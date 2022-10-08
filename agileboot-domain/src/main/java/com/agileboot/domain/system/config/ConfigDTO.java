package com.agileboot.domain.system.config;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.agileboot.orm.entity.SysConfigEntity;
import com.agileboot.orm.enums.dictionary.CommonAnswerEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ConfigDTO {

    public ConfigDTO(SysConfigEntity entity) {

        if (entity != null) {
            configId = entity.getConfigId() + "";
            configName = entity.getConfigName();
            configKey = entity.getConfigKey();
            configValue = entity.getConfigValue();
            configOptions =
                JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                    String.class) : ListUtil.empty();
            isAllowChange = Convert.toInt(entity.getIsAllowChange()) + "";
            isAllowChangeStr = BasicEnumUtil.getDescriptionByBool(CommonAnswerEnum.class, entity.getIsAllowChange());
            remark = entity.getRemark();
            createTime = entity.getCreateTime();
        }
    }

    private String configId;
    private String configName;
    private String configKey;
    private String configValue;
    private List<String> configOptions;
    private String isAllowChange;
    private String isAllowChangeStr;
    private String remark;
    private Date createTime;

}
