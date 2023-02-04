package com.agileboot.admin.controller.tool;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.CharsetUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.core.base.BaseController;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * swagger 接口
 * TODO Swagger这边权限拦截并没有生效，需要改进
 * PreAuthorize注解加在transfer接口上 获取不到登录用户   iframe请求header中没有token
 * @author valarchie
 */
@RestController
public class SwaggerController extends BaseController {

    /**
     * TODO 这个接口没有触发, 后续看如何改进
     */
    @PreAuthorize("@permission.has('tool:swagger:view')")
    @GetMapping("/tool/swagger")
    public String index() {
        return redirect("/doc.html");
    }

    /**
     * 访问首页，提示语
     */
    @GetMapping("/v3/api-docs/{url}")
    public void transfer(HttpServletResponse response, @PathVariable String url) throws IOException {
        response.sendRedirect(AgileBootConfig.getApiDocsPathPrefix() + "/v3/api-docs/" + URLEncodeUtil.encode(url,
            CharsetUtil.CHARSET_UTF_8));
    }

}
