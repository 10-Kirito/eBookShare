package com.example.ebookshare.controller;


//文件上传相关接口

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Files;
import com.example.ebookshare.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${bookpic.upload.path}")
    private String bookpicDownloadPath;

    @Value("${books.upload.path}")
    private String bookDownloadPath;

    /**
     * 文件下载接口：http://124.71.166.37:9090/file/{fileUUid}
     * 这里的fileUUid是我们上传文件的时候自动生成的唯一标识符
     * @param fileUUid
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUid}")
    public void download(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(bookDownloadPath + fileUUid);
        //文件上传路径
        //设置输出流格式
        ServletOutputStream os =response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUid,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("/bookpic/{fileUUid}")
    public void downloadbookPic(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(bookpicDownloadPath + fileUUid);
        //文件上传路径
        //设置输出流格式
        ServletOutputStream os =response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUid,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
