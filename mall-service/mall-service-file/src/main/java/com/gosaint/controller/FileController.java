package com.gosaint.controller;

import java.io.IOException;

import javax.annotation.Resource;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.gosaint.config.FdfsClient;
import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:44 2019/12/18
 * @Modified By:
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/upload")
public class FileController {

    @Resource
    private FdfsClient client ;

    @PostMapping
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String url = client.upload(file);
        return new Result(true, StatusCode.OK, "上传成功",url);
    }
}
