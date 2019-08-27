package com.xiao.amovie.controller.api;

import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.utils.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao
 * @date 2019-08-26 15:39
 */
@RestController
@CrossOrigin(value = "*", allowCredentials = "true")
public class ApiUploadController {

    @Autowired
    private MyProperties myProperties;

    @PostMapping("/api/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {

        String filename = file.getOriginalFilename();
        String filePath = myProperties.getPath();
        File des = new File(filePath);
        if (!des.exists()) {
            des.mkdir();
        }
        //存储在本地的图片地址
        String localPath = filePath + File.separator + filename;
        File dest =new File(localPath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new CommonException("上传图片失败");
        }
        Map map = new HashMap();
        //返回给前端的图片地址：/images/xxx.jpg
        String url = File.separator + "upload" + File.separator + filename;
        map.put("url",url);
        return new ResponseEntity(map, HttpStatus.OK);
    }

}
