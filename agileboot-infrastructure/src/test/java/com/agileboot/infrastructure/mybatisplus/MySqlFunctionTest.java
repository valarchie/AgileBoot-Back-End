package com.agileboot.infrastructure.mybatisplus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MySqlFunctionTest {

    @Test
    void find_in_set() {
        String searchStr = ",2,4,5,9";

        Assertions.assertTrue(MySqlFunction.findInSet("2", searchStr));
        Assertions.assertTrue(MySqlFunction.findInSet("5", searchStr));
        Assertions.assertTrue(MySqlFunction.findInSet("9", searchStr));
    }

}
