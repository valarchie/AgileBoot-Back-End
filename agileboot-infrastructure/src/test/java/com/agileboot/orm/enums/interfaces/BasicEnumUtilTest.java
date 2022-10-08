package com.agileboot.orm.enums.interfaces;


import com.agileboot.orm.enums.dictionary.CommonAnswerEnum;
import org.junit.Assert;
import org.junit.Test;

public class BasicEnumUtilTest {

    @Test
    public void testFromValue() {

        CommonAnswerEnum yes = BasicEnumUtil.fromValue(CommonAnswerEnum.class, 1);
        CommonAnswerEnum no = BasicEnumUtil.fromValue(CommonAnswerEnum.class, 0);

        Assert.assertEquals(yes.description(), "是");
        Assert.assertEquals(no.description(), "否");

    }
}
