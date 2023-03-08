package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.post.PostApplicationService;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.post.query.PostQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.orm.common.enums.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Tag(name = "职位API", description = "职位相关的增删查改")
@RestController
@RequestMapping("/system/post")
@Validated
@RequiredArgsConstructor
public class SysPostController extends BaseController {

    @NonNull
    private PostApplicationService postApplicationService;

    /**
     * 获取岗位列表
     */
    @Operation(summary = "职位列表")
    @PreAuthorize("@permission.has('system:post:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<PostDTO>> list(PostQuery query) {
        PageDTO<PostDTO> pageDTO = postApplicationService.getPostList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @Operation(summary = "职位列表导出")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:post:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PostQuery query) {
        PageDTO<PostDTO> pageDTO = postApplicationService.getPostList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), PostDTO.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @Operation(summary = "职位详情")
    @PreAuthorize("@permission.has('system:post:query')")
    @GetMapping(value = "/{postId}")
    public ResponseDTO<PostDTO> getInfo(@PathVariable Long postId) {
        PostDTO post = postApplicationService.getPostInfo(postId);
        return ResponseDTO.ok(post);
    }

    /**
     * 新增岗位
     */
    @Operation(summary = "添加职位")
    @PreAuthorize("@permission.has('system:post:add')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddPostCommand addCommand) {
        postApplicationService.addPost(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改岗位
     */
    @Operation(summary = "修改职位")
    @PreAuthorize("@permission.has('system:post:edit')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@RequestBody UpdatePostCommand updateCommand) {
        postApplicationService.updatePost(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除岗位
     */
    @Operation(summary = "删除职位")
    @PreAuthorize("@permission.has('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{postIds}")
    public ResponseDTO<Void> remove(@PathVariable List<Long> postIds) {
        postApplicationService.deletePost(new BulkOperationCommand<>(postIds));
        return ResponseDTO.ok();
    }

}
