package com.agileboot.domain.system.loginInfo;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.orm.entity.SysLoginInfoEntity;
import com.agileboot.orm.service.ISysLoginInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class LoginInfoDomainService {

    @Autowired
    private ISysLoginInfoService loginInfoService;

    public PageDTO getLoginInfoList(LoginInfoQuery query) {
        Page<SysLoginInfoEntity> page = loginInfoService.page(query.toPage(), query.toQueryWrapper());
        List<LoginInfoDTO> records = page.getRecords().stream().map(LoginInfoDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public void deleteLoginInfo(BulkOperationCommand<Long> deleteCommand) {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("info_id", deleteCommand.getIds());
        loginInfoService.remove(queryWrapper);
    }

}
