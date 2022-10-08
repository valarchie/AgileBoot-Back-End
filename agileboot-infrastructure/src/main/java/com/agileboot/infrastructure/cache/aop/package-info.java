package com.agileboot.infrastructure.cache.aop;

/**
 * 本来想通过注解的形式 @cacheable来实现缓存
 * 但是MybatisPlus 生成的Service类 里面是空的  不好打注解
 * 并且本项目借鉴CQRS的想法，所有写相关的操作，都放置在domainService当中，而查询可以通过
 * mybatis plus普通的service进行查询。
 * 查询和操作  分别在两个地方  如果用@cacheable注解的话， 在两个地方进行分别注解  很容易疏漏出问题
 * 所以最终还是想采用手动操作缓存的方式
 * 利用三级缓存  map-> guava -> redis 的形式
 */
