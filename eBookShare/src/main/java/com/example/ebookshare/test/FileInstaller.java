package com.example.ebookshare.test;

import cn.hutool.core.io.resource.ResourceUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;

public class FileInstaller {

    /**
     * pdf分片加载的后端实现
     * @param response
     * @param request
     * @throws java.io.FileNotFoundException
     */

    @GetMapping("/loadPDFByPage")
    public void loadPDFByPage(HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException {
        File pdf = ResourceUtils.getFile("classpath:test.pdf");

        try{

        }catch (Exception e)
        {

        }

    }
}
