package com.agileboot.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 程序注解配置
 * 注解EnableAspectJAutoProxy 表示通过aop框架暴露该代理对象,AopContext能够访问
 * 注解MapperScan  指定要扫描的Mapper类的包的路径
 * @author valarchie
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
@MapperScan("com.agileboot.orm.**.mapper")
public class ApplicationConfig {

}
