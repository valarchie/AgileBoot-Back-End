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
// 因为如果直接指定db包   service也会被扫描到  所以通过markerInterface 进行限定
@MapperScan(value = "com.agileboot.**.db", markerInterface = com.baomidou.mybatisplus.core.mapper.BaseMapper.class)
public class ApplicationConfig {

}
