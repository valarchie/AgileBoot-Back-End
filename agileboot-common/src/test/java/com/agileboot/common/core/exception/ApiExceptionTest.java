package com.agileboot.common.core.exception;

import org.junit.Assert;
import org.junit.Test;

public class ApiExceptionTest {


    @Test
    public void testVarargsWithArrayArgs() {
        String errorMsg = "these parameters are null: %s, %s, %s.";

        Object[] array = new Object[] { "param1" , "param2" , "param3"};

        String format1 = String.format(errorMsg, array);
        String format2 = String.format(errorMsg, "param1", "param2", "param3");

        System.out.println(format1);
        System.out.println(format2);

        Assert.assertEquals(format1, format2);
    }

}
