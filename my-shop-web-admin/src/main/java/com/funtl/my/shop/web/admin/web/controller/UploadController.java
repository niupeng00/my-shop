package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器
 * <p>Title: UploadController</p>
 * <p>Description: </p>
 *
 * @version 1.0.0
 * @date 2018/6/27 0:42
 */
@Controller
public class UploadController {

    private static  final String UPLOAD_PATH = "/static/upload/";

    /**
     *
     * @param dropzFile Dropzone
     * @param editorFiles wangEditor
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile[] editorFiles, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        //Dropzone 上传
        if (dropzFile != null) {
            result.put("fileName",writeFile(dropzFile, request));
        }

        //wangEditor 编辑器上传
        if (editorFiles != null && editorFiles.length > 0){
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile editorFile : editorFiles) {
                fileNames.add(writeFile(editorFile, request));
            }

            result.put("errno", 0);
            result.put("data", fileNames);
        }

        return result;
    }

    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {

        // 获取上传的原始文件名
        String fileName = multipartFile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        // 判断路径是否存在, 不存在则创建文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            // 写入文件
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return serverPath + UPLOAD_PATH + file.getName();

    }

    /*public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        //前端上传的文件
        MultipartFile myfile = dropzFile == null ? editorFile : dropzFile;

        // 获取上传的原始文件名
        String fileName = myfile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        // 判断并创建上传用的文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            // 写入文件
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dropzone 上传
        if (dropzFile != null) {
            // 返回 JSON 数据，这里只带入了文件名<文件上传功能>
            result.put("fileName", UPLOAD_PATH + file.getName());
        } else {
            // wangEditor 上传
            *//**
             *  scheme: 服务端提供的协议 http / https
             *  serverName: 服务器名称 localhost / ip / 域名<domain>
             *  serverPort: 服务器端口
             *//*
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            //富文本长传图片功能的返回方式
            result.put("errno", 0);
            result.put("data", new String[]{serverPath + UPLOAD_PATH + file.getName()});
        }
        return result;
    }*/
}
