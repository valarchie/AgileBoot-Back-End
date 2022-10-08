package com.agileboot.common.utils.file;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.constant.Constants;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 *
 * @author ruoyi 待改进
 */
public class FileUploadUtils {

    /**
     * 默认大小 50M
     */
    public static final long MAX_FILE_SIZE = 50 * Constants.MB;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int MAX_FILE_NAME_LENGTH = 100;

    public static final String[] ALLOWED_DOWNLOAD_EXTENSIONS = {
        // 图片
        "bmp", "gif", "jpg", "jpeg", "png",
        // word excel powerpoint
        "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
        // 压缩文件
        "rar", "zip", "gz", "bz2",
        // 视频格式
        "mp4", "avi", "rmvb",
        // pdf
        "pdf"};

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = AgileBootConfig.getProfile();


    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     */
    public static String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, ALLOWED_DOWNLOAD_EXTENSIONS);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     */
    public static String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, ALLOWED_DOWNLOAD_EXTENSIONS);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static String upload(String baseDir, MultipartFile file, String[] allowedExtension)
        throws IOException {

        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FileUploadUtils.MAX_FILE_NAME_LENGTH) {
            throw new ApiException(ErrorCode.Business.UPLOAD_FILE_NAME_EXCEED_MAX_LENGTH, MAX_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = generateFilename(file);

        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        return getPathFileName(baseDir, fileName);
    }

    /**
     * 编码文件名
     */
    public static String generateFilename(MultipartFile file) {
        return StrUtil.format("{}_{}_{}.{}", DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN),
            FilenameUtils.getBaseName(file.getOriginalFilename()), IdUtil.simpleUUID(), getExtension(file));
    }

    public static File getAbsoluteFile(String uploadDir, String fileName) {
        File desc = new File(uploadDir + File.separator + fileName);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static String getPathFileName(String uploadDir, String fileName) {
        String currentDir = StrUtil.strip(uploadDir, AgileBootConfig.getProfile() + "/");
        return Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     */
    public static void assertAllowed(MultipartFile file, String[] allowedExtension) {
        long size = file.getSize();
        if (size > MAX_FILE_SIZE) {
            throw new ApiException(ErrorCode.Business.UPLOAD_FILE_SIZE_EXCEED_MAX_SIZE, MAX_FILE_SIZE / Constants.MB);
        }

        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            throw new ApiException(ErrorCode.Business.UPLOAD_FILE_TYPE_NOT_ALLOWED,
                StrUtil.join(",", (Object[]) allowedExtension));
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     */
    public static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        return StrUtil.containsAnyIgnoreCase(extension, allowedExtension);
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StrUtil.isEmpty(extension)) {
            MimeType mimeType = MimeTypeUtils.parseMimeType(Objects.requireNonNull(file.getContentType()));
            extension = mimeType.getSubtype();
        }
        return extension;
    }


    /**
     * 检查文件是否可下载
     *
     * @param resource 需要下载的文件
     * @return true 正常 false 非法
     */
    public static boolean isAllowDownload(String resource) {
        // 禁止目录上跳级别
        return !StrUtil.contains(resource, "..") &&
            // 检查允许下载的文件规则
            StrUtil.containsAnyIgnoreCase(FileNameUtil.getSuffix(resource), ALLOWED_DOWNLOAD_EXTENSIONS);
    }


    /**
     * 下载文件名重新编码
     *
     * @param response 响应对象
     * @param realFileName 真实文件名
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) {
        String fileNameUrlEncoded = URLUtil.encode(realFileName, CharsetUtil.CHARSET_UTF_8);

        String contentDisposition = String.format("attachment; filename=%s;filename*=utf-8''%s", fileNameUrlEncoded,
            fileNameUrlEncoded);

        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition,download-filename");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
        response.setHeader("download-filename", fileNameUrlEncoded);
    }


}
