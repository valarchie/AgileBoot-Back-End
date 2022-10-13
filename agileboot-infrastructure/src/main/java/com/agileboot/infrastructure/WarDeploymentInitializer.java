package com.agileboot.infrastructure;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 对于Spring Boot应用，我们一般会打成jar包使用内置容器运行，但是有时候我们想要像使用传统spring web项目一样，
 * 将Spring Boot应用打成WAR包，然后部署到外部容器运行，那么我们传统的使用Main类启动的方式稍显蹩脚，
 * 因为外部容器无法识别到应用启动类，需要在应用中继承SpringBootServletInitializer类，然后重写config方法，
 * 将其指向应用启动类。
 * @author valarchie
 */
public class WarDeploymentInitializer extends SpringBootServletInitializer {
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(AgileBootApplication.class);
//    }
}
