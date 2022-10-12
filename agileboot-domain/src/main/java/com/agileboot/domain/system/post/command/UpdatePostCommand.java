package com.agileboot.domain.system.post.command;

import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.model.PostModel;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdatePostCommand extends AddPostCommand {

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
