package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.domain.system.post.AddPostCommand;
import com.agileboot.domain.system.post.PostDTO;
import com.agileboot.domain.system.post.PostDomainService;
import com.agileboot.domain.system.post.PostQuery;
import com.agileboot.domain.system.post.UpdatePostCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/system/post")
@Validated
public class SysPostController extends BaseController {

    @Autowired
    private PostDomainService postDomainService;

    /**
     * 获取岗位列表
     */
    @PreAuthorize("@ss.hasPerm('system:post:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(PostQuery query) {
        PageDTO pageDTO = postDomainService.getPostList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "岗位管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPerm('system:post:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PostQuery query) {
        PageDTO pageDTO = postDomainService.getPostList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), PostDTO.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:post:query')")
    @GetMapping(value = "/{postId}")
    public ResponseDTO getInfo(@PathVariable Long postId) {
        PostDTO post = postDomainService.getPostInfo(postId);
        return ResponseDTO.ok(post);
    }

    /**
     * 新增岗位
     */
    @PreAuthorize("@ss.hasPerm('system:post:add')")
    @AccessLog(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseDTO add(@RequestBody AddPostCommand addCommand) {
        postDomainService.addPost(addCommand, AuthenticationUtils.getLoginUser());
        return ResponseDTO.ok();
    }

    /**
     * 修改岗位
     */
    @PreAuthorize("@ss.hasPerm('system:post:edit')")
    @AccessLog(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseDTO edit(@RequestBody UpdatePostCommand updateCommand) {
        postDomainService.updatePost(updateCommand, AuthenticationUtils.getLoginUser());
        return ResponseDTO.ok();
    }

    /**
     * 删除岗位
     */
    @PreAuthorize("@ss.hasPerm('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{postIds}")
    public ResponseDTO remove(@PathVariable List<Long> postIds) {
        postDomainService.deletePost(new BulkOperationCommand<>(postIds));
        return ResponseDTO.ok();
    }

}
