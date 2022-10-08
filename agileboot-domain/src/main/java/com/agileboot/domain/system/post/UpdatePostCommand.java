package com.agileboot.domain.system.post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdatePostCommand extends AddPostCommand{

    @NotNull(message = "岗位ID不能为空")
    @Positive
    private Long postId;

    @Override
    public PostModel toModel() {
        PostModel postModel = super.toModel();
        postModel.setPostId(postId);
        return postModel;
    }

}
