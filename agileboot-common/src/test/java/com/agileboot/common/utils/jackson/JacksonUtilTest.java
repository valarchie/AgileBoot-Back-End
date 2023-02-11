package com.agileboot.common.utils.jackson;

import cn.hutool.core.date.DateUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author duanxinyuan 2019/1/21 18:17
 */
public class JacksonUtilTest {

    @Test
    public void testObjectToJson() {
        Person person = Person.newPerson();

        String jacksonStr = JacksonUtil.to(person);
        Assert.assertEquals(DateUtil.formatDateTime(person.getDate()), JacksonUtil.getAsString(jacksonStr, "date"));
        Assert.assertEquals(DateUtil.formatLocalDateTime(person.getLocalDateTime()),
            JacksonUtil.getAsString(jacksonStr, "localDateTime"));
        Assert.assertEquals(person.getName(), JacksonUtil.getAsString(jacksonStr, "name"));
        Assert.assertEquals(person.getAge(), JacksonUtil.getAsInt(jacksonStr, "age"));
        Assert.assertEquals(person.isMan(), JacksonUtil.getAsBoolean(jacksonStr, "man"));
        Assert.assertEquals(person.getMoney(), JacksonUtil.getAsBigDecimal(jacksonStr, "money"));
        Assert.assertEquals(person.getTrait(), JacksonUtil.getAsList(jacksonStr, "trait", String.class));

        Assert.assertNotNull(JacksonUtil.getAsString(jacksonStr, "name"));
    }

    /**
     * 测试兼容情况
     */
    @Test
    public void testAllPrimitiveTypeToJson() {
        String json = "{\n"
            + "\"code\": \"200\",\n"
            + "\"id\": \"2001215464647687987\",\n"
            + "\"message\": \"success\",\n"
            + "\"amount\": \"1.12345\",\n"
            + "\"amount1\": \"0.12345\",\n"
            + "\"isSuccess\": \"true\",\n"
            + "\"isSuccess1\": \"1\",\n"
            + "\"key\": \"8209167202090377654857374178856064487200234961995543450245362822537162918731039965956758726661669012305745755921310000297396309887550627402157318910581311\"\n"
            + "}";
        Assert.assertEquals(200, JacksonUtil.getAsInt(json, "code"));
        Assert.assertEquals(2001215464647687987L,JacksonUtil.getAsLong(json, "id"));
        Assert.assertEquals("success", JacksonUtil.getAsString(json, "message"));
        Assert.assertEquals(new BigDecimal("1.12345"), JacksonUtil.getAsBigDecimal(json, "amount"));
        Assert.assertEquals(new BigDecimal("0.12345"), JacksonUtil.getAsBigDecimal(json, "amount1"));
        Assert.assertEquals(1.12345d, JacksonUtil.getAsDouble(json, "amount"), 0.00001);
        Assert.assertEquals(0.12345d, JacksonUtil.getAsDouble(json, "amount1"), 0.00001);
        Assert.assertTrue(JacksonUtil.getAsBoolean(json, "isSuccess"));
        Assert.assertTrue(JacksonUtil.getAsBoolean(json, "isSuccess1"));
        Assert.assertEquals(new BigInteger(
            "8209167202090377654857374178856064487200234961995543450245362822537162918731039965956758726661669012305745755921310000297396309887550627402157318910581311"),
            JacksonUtil.getAsBigInteger(json, "key"));
        Assert.assertEquals("1", JacksonUtil.getAsString(json, "isSuccess1"));
    }

}
