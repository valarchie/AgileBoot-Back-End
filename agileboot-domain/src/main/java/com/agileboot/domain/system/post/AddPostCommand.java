package com.agileboot.domain.system.post;

import cn.hutool.core.convert.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AddPostCommand {

    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64个字符")
    protected String postCode;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 64, message = "岗位名称长度不能超过64个字符")
    protected String postName;

    /**
     * 岗位排序
     */
    @NotNull(message = "显示顺序不能为空")
    protected Integer postSort;

    protected String remark;

    @PositiveOrZero
    protected String status;

    public PostModel toModel() {
        PostModel model = new PostModel();

        model.setPostCode(postCode);
        model.setPostName(postName);
        model.setPostSort(postSort);
        model.setRemark(remark);
        model.setStatus(Convert.toInt(status));

        return model;
    }

}
