package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.notice.NoticeApplicationService;
import com.agileboot.domain.system.notice.command.NoticeAddCommand;
import com.agileboot.domain.system.notice.command.NoticeUpdateCommand;
import com.agileboot.domain.system.notice.dto.NoticeDTO;
import com.agileboot.domain.system.notice.query.NoticeQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.annotations.Resubmit;
import com.agileboot.orm.common.enums.BusinessTypeEnum;
import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
 * 公告 信息操作处理
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system/notice")
@Validated
@RequiredArgsConstructor
public class SysNoticeController extends BaseController {

    @NonNull
    private NoticeApplicationService noticeApplicationService;

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@permission.has('system:notice:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(NoticeQuery query) {
        PageDTO pageDTO = noticeApplicationService.getNoticeList(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 获取通知公告列表
     * 从从库获取数据 例子 仅供参考
     */
    @DS("slave")
    @PreAuthorize("@permission.has('system:notice:list')")
    @GetMapping("/listFromSlave")
    public ResponseDTO<PageDTO> listFromSlave(NoticeQuery query) {
        PageDTO pageDTO = noticeApplicationService.getNoticeList(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public ResponseDTO<NoticeDTO> getInfo(@PathVariable @NotNull @Positive Long noticeId) {
        return ResponseDTO.ok(noticeApplicationService.getNoticeInfo(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Resubmit(interval = 60)
    @PreAuthorize("@permission.has('system:notice:add')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<?> add(@RequestBody NoticeAddCommand addCommand) {
        noticeApplicationService.addNotice(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@permission.has('system:notice:edit')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<?> edit(@RequestBody NoticeUpdateCommand updateCommand) {
        noticeApplicationService.updateNotice(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@permission.has('system:notice:remove')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{noticeIds}")
    public ResponseDTO<?> remove(@PathVariable List<Integer> noticeIds) {
        noticeApplicationService.deleteNotice(new BulkOperationCommand<>(noticeIds));
        return ResponseDTO.ok();
    }



}
