package com.gosaint.config;

import java.io.IOException;

import javax.annotation.Resource;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.models.auth.In;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 22:35 2019/12/18
 * @Modified By:
 */
@Component
public class FdfsClient {


    @Value("${fdfs.reqHost}")
    private String host;

    @Value("${fdfs.reqPort}")
    private Integer port;


    @Resource
    private FastFileStorageClient storageClient ;

    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename().
                substring(file.getOriginalFilename().
                        lastIndexOf(".") + 1);
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                file.getInputStream(),
                file.getSize(),originalFilename , null);
        return get(storePath.getFullPath());
    }

    private String get(String url){
        return "http://"+host+":"+port+"/"+url;
    }
}
