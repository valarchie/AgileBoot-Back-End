package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.domain.system.notice.NoticeAddCommand;
import com.agileboot.domain.system.notice.NoticeDTO;
import com.agileboot.domain.system.notice.NoticeDomainService;
import com.agileboot.domain.system.notice.NoticeQuery;
import com.agileboot.domain.system.notice.NoticeUpdateCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
 * 公告 信息操作处理
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system/notice")
@Validated
public class SysNoticeController extends BaseController {

    @Autowired
    private NoticeDomainService noticeDomainService;

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@ss.hasPerm('system:notice:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(NoticeQuery query) {
        PageDTO pageDTO = noticeDomainService.getNoticeList(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public ResponseDTO<NoticeDTO> getInfo(@PathVariable @NotNull @Positive Long noticeId) {
        return ResponseDTO.ok(noticeDomainService.getNoticeInfo(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:add')")
    @AccessLog(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseDTO add(@RequestBody NoticeAddCommand addCommand) {
        noticeDomainService.addNotice(addCommand, AuthenticationUtils.getLoginUser());
        return ResponseDTO.ok();
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:edit')")
    @AccessLog(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseDTO edit(@RequestBody NoticeUpdateCommand updateCommand) {
        noticeDomainService.updateNotice(updateCommand, AuthenticationUtils.getLoginUser());
        return ResponseDTO.ok();
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:remove')")
    @AccessLog(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public ResponseDTO remove(@PathVariable List<Long> noticeIds) {
        noticeDomainService.deleteNotice(new BulkOperationCommand<>(noticeIds));
        return ResponseDTO.ok();
    }



}
