package com.agileboot.framework.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.StrUtil;
import com.agileboot.infrastructure.config.captcha.CaptchaMathTextCreator;
import org.junit.Assert;
import org.junit.Test;

public class CaptchaMathTextCreatorTest {

    @Test
    public void test() {
        CaptchaMathTextCreator captchaMathTextCreator = new CaptchaMathTextCreator();
        for (int i = 0; i < 50; i++) {
            validateExpressionAndResult(captchaMathTextCreator.getText());
        }
    }

    private void validateExpressionAndResult(String expression) {
        String[] expressionAndResult = expression.split("@");
        Assert.assertEquals(expressionAndResult.length, 2);
        System.out.println(expressionAndResult[0] + "  answer is " + expressionAndResult[1]);
        String safeExpression = StrUtil.removeSuffix(expressionAndResult[0], "=?");
        Assert.assertEquals(Convert.toInt(Calculator.conversion(safeExpression)) + "", expressionAndResult[1]);
    }
}
