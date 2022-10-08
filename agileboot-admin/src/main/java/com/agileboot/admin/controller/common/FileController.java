package com.agileboot.admin.controller.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.admin.response.UploadDTO;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.file.FileUploadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用请求处理
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> fileDownload(String fileName, HttpServletResponse response) {
        try {
            if (!FileUploadUtils.isAllowDownload(fileName)) {
                throw new Exception(StrUtil.format("文件名称({})非法，不允许下载。 ", fileName));
            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = AgileBootConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", String.format("attachment;filename=%s", realFileName));
            return new ResponseEntity<>(FileUtil.readBytes(filePath), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("下载文件失败", e);
            return null;
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public ResponseDTO<UploadDTO> uploadFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new ApiException(ErrorCode.Business.UPLOAD_FILE_IS_EMPTY);
        }

        // 上传文件路径
        String filePath = AgileBootConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);

        String url = ServletHolderUtil.getContextUrl() + fileName;

        UploadDTO uploadDTO = UploadDTO.builder()
            .url(url)
            .fileName(fileName)
            .newFileName(FileNameUtil.getName(fileName))
            .originalFilename(file.getOriginalFilename()).build();

        return ResponseDTO.ok(uploadDTO);
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public ResponseDTO<List<UploadDTO>> uploadFiles(List<MultipartFile> files) throws Exception {
        if (CollUtil.isEmpty(files)) {
            throw new ApiException(ErrorCode.Business.UPLOAD_FILE_IS_EMPTY);
        }

        // 上传文件路径
        String filePath = AgileBootConfig.getUploadPath();

        List<UploadDTO> uploads = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = ServletHolderUtil.getContextUrl() + fileName;
                UploadDTO uploadDTO = UploadDTO.builder()
                    .url(url)
                    .fileName(fileName)
                    .newFileName(FileNameUtil.getName(fileName))
                    .originalFilename(file.getOriginalFilename()).build();

                uploads.add(uploadDTO);

            }
        }
        return ResponseDTO.ok(uploads);
    }

}
