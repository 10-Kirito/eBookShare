package com.example.ebookshare.controller;


//文件上传相关接口

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Admins;
import com.example.ebookshare.entity.Files;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.mapper.AdminsMapper;
import com.example.ebookshare.mapper.FileMapper;
import com.example.ebookshare.mapper.UsersMapper;
import com.example.ebookshare.service.IAdminsService;
import com.example.ebookshare.service.impl.AdminsServiceImpl;
import com.example.ebookshare.service.impl.UsersServiceImpl;
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

    @Value("${auditbooks.upload.path}")
    private String auditBookDownloadPAth;

    @Value("${auditbookpic.upload.path}")
    private String auditBookPicDownloadPAth;

    @Value("${files.upload.path}")
    private String avartarPAth;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersServiceImpl usersService;

    @Resource
    private IAdminsService adminsService;

    @Resource
    private AdminsMapper adminsMapper;


    @Value("${productimg.upload.path}")
    private String productPAth;

    @Value("${lunboimg.upload.path}")
    private String lunboPAth;

    @Resource
    private FileMapper fileMapper;


    @PostMapping("/product/upload")
    public String  productUpload(@RequestParam MultipartFile file) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);

        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(productPAth + fileUUid);

        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }

        //实现：对于相同内容不同文件名的文件，因为md5一样，在数据库中每个有一个记录，但是在磁盘中，只会存在一个最新的文件
        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);

        //把获取到的文件存储到磁盘目录
        url = "http://localhost:9091/file/product/"+fileUUid;


        return url; //文件下载链接
        //上传成功后返回url
    }


    @GetMapping("/product/{fileUUid}")
    public void downloadproduct(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(productPAth + fileUUid);
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

    @PostMapping("/lunbo/upload")
    public String  lunboUpload(@RequestParam MultipartFile file) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);

        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(lunboPAth + fileUUid);

        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }

        //实现：对于相同内容不同文件名的文件，因为md5一样，在数据库中每个有一个记录，但是在磁盘中，只会存在一个最新的文件
        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);

        //把获取到的文件存储到磁盘目录
        url = "http://localhost:9091/file/lunbo/"+fileUUid;


        return url; //文件下载链接
        //上传成功后返回url
    }


    @GetMapping("/lunbo/{fileUUid}")
    public void downloadlunbo(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(lunboPAth + fileUUid);
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







    /**
     * 文件下载接口：http://124.71.166.37:9090/file/{fileUUid}
     * 这里的fileUUid是我们上传文件的时候自动生成的唯一标识符
     * @param fileUUid
     * @param response
     * @throws IOException
     */
    @GetMapping("/audit/{fileUUid}")
    public void downloadAuditbook(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(auditBookDownloadPAth + fileUUid);
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

    @GetMapping("/bookpic/audit/{fileUUid}")
    public void downloadAuditbookPic(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(auditBookPicDownloadPAth + fileUUid);
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

    @GetMapping("/avartar/{fileUUid}")
    public void downloadavartar(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(avartarPAth + fileUUid);
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
    @PostMapping("/avartar/upload")
    public String  upload(@RequestParam MultipartFile file,@RequestParam("id") String userid) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);
        long size = file.getSize();

        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(avartarPAth + fileUUid);

        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }

        //实现：对于相同内容不同文件名的文件，因为md5一样，在数据库中每个有一个记录，但是在磁盘中，只会存在一个最新的文件
        String url;
        String md5;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        md5 = SecureUtil.md5(uploadFile);
        //数据库查询是否存在相同的记录
//        Files dbFiles = getFileByMd5(md5);
//        if (dbFiles != null){
//            url = dbFiles.getUrl();
//            //删除之前已存在的重复文件，以便于上传最新版文件
//            uploadFile.delete();
//        }else {
        //数据库不存在重复的文件
        //把获取到的文件存储到磁盘目录
//            url = "http://124.71.166.37:9091/avartar/file/"+fileUUid;
        url = "http://124.71.166.37:9091/file/avartar/"+fileUUid;
//        }
        //获取文件url
        //把获取到的文件存储到磁盘目录中

        //文件路径
        //存储数据库
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userid);
        Users users = usersMapper.selectOne(queryWrapper);
        if(users == null){
            return null;
        }
        users.setAvatarurl(url);
        usersService.saveOrUpdate(users);
        return url; //文件下载链接
        //上传成功后返回url
    }

    @PostMapping("/adminavartar/upload")
    public String  adminAvartarUpload(@RequestParam MultipartFile file,@RequestParam("id") String adminid) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);
        long size = file.getSize();

        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(avartarPAth + fileUUid);

        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }

        //实现：对于相同内容不同文件名的文件，因为md5一样，在数据库中每个有一个记录，但是在磁盘中，只会存在一个最新的文件
        String url;
        String md5;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        md5 = SecureUtil.md5(uploadFile);
        //数据库查询是否存在相同的记录
//        Files dbFiles = getFileByMd5(md5);
//        if (dbFiles != null){
//            url = dbFiles.getUrl();
//            //删除之前已存在的重复文件，以便于上传最新版文件
//            uploadFile.delete();
//        }else {
        //数据库不存在重复的文件
        //把获取到的文件存储到磁盘目录
//            url = "http://124.71.166.37:9091/avartar/file/"+fileUUid;
        url = "http://124.71.166.37:9091/file/avartar/"+fileUUid;
//        }
        //获取文件url
        //把获取到的文件存储到磁盘目录中

        //文件路径
        //存储数据库
        QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",adminid);
        Admins admins = adminsMapper.selectOne(queryWrapper);
        if(admins == null){
            return null;
        }
        admins.setAvatarurl(url);
        adminsService.saveOrUpdate(admins);
        return url; //文件下载链接
        //上传成功后返回url
    }


}
