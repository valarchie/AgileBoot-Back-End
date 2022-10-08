package com.agileboot.domain.system.notice;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysNoticeEntity;
import com.agileboot.orm.service.ISysNoticeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class NoticeDomainService {

    @Autowired
    private ISysNoticeService noticeService;

    public PageDTO getNoticeList(NoticeQuery query) {
        Page<SysNoticeEntity> page = noticeService.page(query.toPage(), query.toQueryWrapper());
        List<NoticeDTO> records = page.getRecords().stream().map(NoticeDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }


    public NoticeDTO getNoticeInfo(Long id) {
        SysNoticeEntity byId = noticeService.getById(id);
        return new NoticeDTO(byId);
    }


    public void addNotice(NoticeAddCommand addCommand, LoginUser loginUser) {
        NoticeModel noticeModel = addCommand.toModel();

        noticeModel.checkFields();

        noticeModel.logCreator(loginUser);

        noticeModel.insert();
    }


    public void updateNotice(NoticeUpdateCommand updateCommand, LoginUser loginUser) {
        SysNoticeEntity byId = noticeService.getById(updateCommand.getNoticeId());

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, updateCommand.getNoticeId(), "通知公告");
        }

        NoticeModel noticeModel = updateCommand.toModel();

        noticeModel.checkFields();

        noticeModel.logUpdater(loginUser);

        noticeModel.updateById();
    }

    public void deleteNotice(BulkOperationCommand<Long> deleteCommand) {
        noticeService.removeBatchByIds(deleteCommand.getIds());
    }




}
