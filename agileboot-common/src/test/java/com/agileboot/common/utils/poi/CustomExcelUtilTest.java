package com.agileboot.common.utils.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HtmlUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomExcelUtilTest {

    @Test
    void testImportAndExport() {
        PostDTO post1 = new PostDTO(1L, "admin1", "管理员1", "1", "无备注", "1", "正常");
        PostDTO post2 = new PostDTO(2L, "admin2", "管理员2<script>alert(1)</script>", "2", "无备注", "1", "正常");

        List<PostDTO> postDTOList = new ArrayList<>();
        postDTOList.add(post1);
        postDTOList.add(post2);

        File file = FileUtil.createTempFile();

        CustomExcelUtil.writeToOutputStream(postDTOList, PostDTO.class, FileUtil.getOutputStream(file));

        List<PostDTO> postListFromExcel = CustomExcelUtil.readFromInputStream(PostDTO.class, FileUtil.getInputStream(file));

        PostDTO post1fromExcel = postListFromExcel.get(0);
        PostDTO post2fromExcel = postListFromExcel.get(1);

        Assertions.assertEquals(post1.getPostId(), post1fromExcel.getPostId());
        Assertions.assertEquals(post1.getPostCode(), post1fromExcel.getPostCode());
        Assertions.assertEquals(post2.getPostId(), post2fromExcel.getPostId());
        Assertions.assertEquals(post2.getPostCode(), post2fromExcel.getPostCode());
        // 检查脚本注入的字符串是否被去除
        Assertions.assertNotEquals(post2.getPostName(), post2fromExcel.getPostName());
        Assertions.assertEquals(HtmlUtil.cleanHtmlTag(post2.getPostName()), post2fromExcel.getPostName());
    }

}
