package com.agileboot.domain.system.post;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.post.model.PostModel;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.post.query.PostQuery;
import com.agileboot.orm.system.entity.SysPostEntity;
import com.agileboot.orm.system.service.ISysPostService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class PostApplicationService {

    @NonNull
    private PostModelFactory postModelFactory;

    @NonNull
    private ISysPostService postService;

    public PageDTO<PostDTO> getPostList(PostQuery query) {
        Page<SysPostEntity> page = postService.page(query.toPage(), query.toQueryWrapper());
        List<PostDTO> records = page.getRecords().stream().map(PostDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public PostDTO getPostInfo(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        return new PostDTO(byId);
    }

    public void addPost(AddPostCommand addCommand) {
        PostModel postModel = postModelFactory.create();
        postModel.loadFromAddCommand(addCommand);

        postModel.checkPostNameUnique();
        postModel.checkPostCodeUnique();

        postModel.insert();
    }

    public void updatePost(UpdatePostCommand updateCommand) {
        PostModel postModel = postModelFactory.loadById(updateCommand.getPostId());
        postModel.loadFromUpdateCommand(updateCommand);

        postModel.checkPostNameUnique();
        postModel.checkPostCodeUnique();

        postModel.updateById();
    }


    public void deletePost(BulkOperationCommand<Long> deleteCommand) {
        for (Long id : deleteCommand.getIds()) {
            PostModel postModel = postModelFactory.loadById(id);
            postModel.checkCanBeDelete();
        }

        postService.removeBatchByIds(deleteCommand.getIds());
    }

}
