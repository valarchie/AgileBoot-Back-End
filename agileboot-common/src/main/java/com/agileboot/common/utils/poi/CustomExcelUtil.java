package com.agileboot.common.utils.poi;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.annotation.ExcelSheet;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author valarchie
 */
public class CustomExcelUtil {


    public static void writeToResponse(List<?> list, Class clazz, HttpServletResponse response) {

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter();

        ExcelSheet sheetAnno = (ExcelSheet)clazz.getAnnotation(ExcelSheet.class);

        if (sheetAnno != null) {
            // 默认的sheetName是 sheet1
            writer.renameSheet(sheetAnno.name());
        }

        Field[] fields = clazz.getDeclaredFields();

        //自定义标题别名
        for (Field field : fields) {
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                writer.addHeaderAlias(field.getName(), annotation.name());
            }
        }

        // 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);

        // 合并单元格后的标题行，使用默认标题样式
        // writer.merge(4, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);

        try {
            writer.flush(response.getOutputStream(), true);
        } catch (IOException e) {
            writer.close();
            e.printStackTrace();
        }

    }

    public static List<?> readFromResponse(Class clazz,  MultipartFile file) {

        ExcelReader reader = null;
        try {
            reader = ExcelUtil.getReader(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Field[] fields = clazz.getDeclaredFields();

        //自定义标题别名
        if (fields != null) {
            for (Field field : fields) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                if (annotation != null) {
                    reader.addHeaderAlias(annotation.name(), field.getName());
                }
            }
        }

        return reader.read(0, 1, clazz);
    }



}
