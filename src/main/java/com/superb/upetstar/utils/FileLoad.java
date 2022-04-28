package com.superb.upetstar.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author hym
 * @description 文件上传工具类
 */
public class FileLoad {
    /**
     * 上传文件
     */
    public static String uploadFile(MultipartFile file, String type, HttpServletRequest request) {
        //String realPath = request.getServletContext().getRealPath("/");
        //String uploadPath = realPath.substring(0, realPath.indexOf("upetstar")) + "/upload/";
        String picName = type + "_" + UUID.randomUUID();
        String fileName = file.getOriginalFilename();
        String substring = fileName.substring(fileName.lastIndexOf("."));
        try {
            file.transferTo(new File("d:\\" + picName + substring));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http:localhost:3306/upload/" + picName + substring;
    }

}
