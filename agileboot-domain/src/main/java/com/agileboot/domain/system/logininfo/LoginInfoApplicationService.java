package com.agileboot.domain.system.logininfo;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.logininfo.dto.LoginInfoDTO;
import com.agileboot.domain.system.logininfo.query.LoginInfoQuery;
import com.agileboot.orm.system.entity.SysLoginInfoEntity;
import com.agileboot.orm.system.service.ISysLoginInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class LoginInfoApplicationService {

    @NonNull
    private ISysLoginInfoService loginInfoService;

    public PageDTO<LoginInfoDTO> getLoginInfoList(LoginInfoQuery query) {
        Page<SysLoginInfoEntity> page = loginInfoService.page(query.toPage(), query.toQueryWrapper());
        List<LoginInfoDTO> records = page.getRecords().stream().map(LoginInfoDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void deleteLoginInfo(BulkOperationCommand<Long> deleteCommand) {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("info_id", deleteCommand.getIds());
        loginInfoService.remove(queryWrapper);
    }

}
