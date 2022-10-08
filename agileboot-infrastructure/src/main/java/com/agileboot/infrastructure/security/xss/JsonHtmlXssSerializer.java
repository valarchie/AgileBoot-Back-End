package com.agileboot.infrastructure.security.xss;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class JsonHtmlXssSerializer extends JsonDeserializer<String> {

    public JsonHtmlXssSerializer() {
        super();
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if( value != null) {
            // 去除掉html标签    如果想要转义的话  可使用 HtmlUtil.escape()
            return HtmlUtil.cleanHtmlTag(value);
        }
        return null;
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }

//    @Override
//    public void deserialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        if (value != null) {
//            String escapeStr = HtmlUtil.escape(value);
//            gen.writeString(escapeStr);
//        }
//    }
}
