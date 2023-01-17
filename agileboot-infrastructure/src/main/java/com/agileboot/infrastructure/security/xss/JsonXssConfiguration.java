package com.agileboot.infrastructure.security.xss;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author valarchie
 */
@Configuration
public class JsonXssConfiguration implements Jackson2ObjectMapperBuilderCustomizer{

//    这种配置方式会覆盖 yml中的jackson配置， 使用下面的customize配置则不会
//    @Bean
//    Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.deserializers(new JsonHtmlXssTrimSerializer());
//        return builder;
//    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.deserializers(new JsonHtmlXssTrimSerializer());
    }

}
