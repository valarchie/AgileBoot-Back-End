package com.agileboot.infrastructure.security.xss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author valarchie
 */
@Configuration
public class JsonXssConfiguration {

    @Bean
    Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializers(new JsonHtmlXssSerializer());
        return builder;
    }


}
