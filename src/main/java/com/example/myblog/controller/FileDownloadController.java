package com.example.myblog.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.myblog.common.AjaxResult;
import com.example.myblog.common.ApplicationVariable;
import com.example.myblog.common.UserSessionTools;
import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName: FileController
 * @Description: 存储头像到阿里云oss控制类
 * @Date 2024/2/6 12:30
 */
@RestController
@RequestMapping("file")
public class FileDownloadController {
    private String endpoint = "XXX";
    private String keyId = "XXX";
    private String keySecret = "XXX";
    private String bucketName = "XXX";

    @Autowired
    private UserService userService;

    @RequestMapping("photo")
    public AjaxResult handleFileUpload(@RequestParam("file") MultipartFile file,
                                       HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return AjaxResult.fail(-1, "参数有误");
        }

        // 获取文件后缀名
        String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        // 创建文件名避免重复
        fileName = UUID.randomUUID() + fileName;

        // 上传文件到阿里云OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);
        ossClient.putObject(bucketName, fileName, file.getInputStream());

        // 将文件名保存到数据库
        User user = UserSessionTools.getLoginUser(request);
        int ret = userService.savePhoto(fileName, user.getId());
        if (ret < 1) {
            return AjaxResult.fail(-1, "保存失败");
        }

        // 更新会话
        user.setPhoto(fileName);
        HttpSession session = request.getSession();
        session.setAttribute(ApplicationVariable.SESSION_KEY_USER, user);

        return AjaxResult.success(1);
    }
}

