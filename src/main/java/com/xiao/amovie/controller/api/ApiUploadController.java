package com.xiao.amovie.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author xiao
 * @date 2019-08-26 15:39
 */
@RestController
@CrossOrigin
public class ApiUploadController {

    @PostMapping("/upload")
    public File upload(@RequestParam("file") MultipartFile file) {

        // 获取配置中的 服务器文件存储路径
        //String uploadFilePath = ConstantConfig.UPLOAD_FILE_PYSICAlLPATH;

        String filename = file.getOriginalFilename();
        String filePath = "C:\\Users\\xiao\\upload";
        File dest = new File(filePath + File.separator + filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    /**
     * 获取服务部署根路径 http:// + ip + port
     *
     * @param request
     * @return
     */
    private String getServerIPPort(HttpServletRequest request) {
        //+ ":" + request.getServerPort()
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    /**
     * 获取文件回调url
     *
     * @param serverName
     *            服务路径
     * @param fileName
     *            文件名
     * @return 回调url
     */
    private String getBackUrl(String serverName, String fileName) {
        StringBuffer backUrl = new StringBuffer();

        //String uploadFileVirPath = ConstantConfig.UPLOAD_FILE_VIRTUALPATH;
        //backUrl.append(serverName).append(uploadFileVirPath).append("/").append(fileName);

        return backUrl.toString();
    }
}
