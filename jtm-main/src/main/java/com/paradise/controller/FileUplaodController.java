package com.paradise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class FileUplaodController {
    private final static Logger logger = LoggerFactory.getLogger(FileUplaodController.class);

    @Value("{filePath}")
    public String rootPath;

    @RequestMapping("/upload")
    public String index() {
        return "upload";
    }

    @RequestMapping("/mutipleFileUpload")
    public String muitipleFile() {
        return "mutipleFileUpload";
    }

    //    MultipartFile[] pic, MultipartFile[] pdf,
//    https://www.cnblogs.com/wangxin37/p/6398717.html
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String getFile(HttpServletRequest request) throws IOException {
        List<MultipartFile> pic = null;
        List<MultipartFile> pdf = null;

        // 检测是否为上传请求
        String contentType = request.getContentType();
        if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            pic = multipartRequest.getFiles("pic");
            pdf = multipartRequest.getFiles("pdf");
        }
        for (MultipartFile m : pic) {
            String content = m.getContentType();
            String name = m.getName();
            String filename = m.getOriginalFilename();
            m.getSize();
            InputStream in =  m.getInputStream();
            String path = (new File("")).getAbsolutePath();
            String filePath = path + "//" + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            fileOutputStream.write(m.getBytes());
        }
        for (MultipartFile m : pdf) {

        }
        String desc = request.getParameter("desc");
        String lables = request.getParameter("labels");

        return "";
    }
}
