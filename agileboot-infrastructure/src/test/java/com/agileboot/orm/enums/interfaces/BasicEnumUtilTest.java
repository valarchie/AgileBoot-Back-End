package com.agileboot.orm.enums.interfaces;


import com.agileboot.orm.common.enums.YesOrNoEnum;
import com.agileboot.orm.common.util.BasicEnumUtil;
import org.junit.Assert;
import org.junit.Test;

public class BasicEnumUtilTest {

    @Test
    public void testFromValue() {

        YesOrNoEnum yes = BasicEnumUtil.fromValue(YesOrNoEnum.class, 1);
        YesOrNoEnum no = BasicEnumUtil.fromValue(YesOrNoEnum.class, 0);

        Assert.assertEquals("是", yes.description());
        Assert.assertEquals("否", no.description());

    }

    @Test
    public void testFromBool() {

        String yesDescription = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, true);
        String noDescription = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, false);

        Assert.assertEquals("是", yesDescription);
        Assert.assertEquals("否", noDescription);

    }
}
