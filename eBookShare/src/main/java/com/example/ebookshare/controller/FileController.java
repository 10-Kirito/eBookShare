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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Value("${url_prefix.remote}")
    private String remote_url_prefix;

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
        url = remote_url_prefix + "file/product/"+fileUUid;

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
        url = remote_url_prefix + "file/lunbo/"+fileUUid;


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


    /**
     * 文件预览接口实现, 仅仅预览文件的前10页
     * @param fileUUid
     * @param response
     * @throws IOException
     */

    @GetMapping("/{fileUUid}/preview")
    public void download(@PathVariable String fileUUid , HttpServletResponse response) throws IOException {

        //根据文件的唯一标识码获取文件
        File uploadFile = new File(bookDownloadPath + fileUUid);
        //文件上传路径
        //设置输出流格式
        ServletOutputStream os =response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUid,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        PDDocument document = PDDocument.load(uploadFile);
        int totalPages = document.getNumberOfPages();

        // 创建一个新的PDF文档，只包含前五页
        PDDocument newDocument = new PDDocument();
        for (int i = 0; i < Math.min(totalPages, 5); i++) {
            newDocument.addPage(document.getPage(i));
        }

        // 将新的PDF文档的字节流写入输出流
        newDocument.save(os);
        newDocument.close();

        os.flush();
        os.close();
    }

    /**
     * 文件的下载接口实现
     * @param fileUUid
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("/{fileUUid}")
    public void downloadfile(@PathVariable String fileUUid , HttpServletResponse response, HttpServletRequest request) throws IOException {
        File pdf = ResourceUtils.getFile(bookDownloadPath + fileUUid);
        try (
                InputStream is = new FileInputStream(pdf);
                BufferedInputStream bis = new BufferedInputStream(is);
                OutputStream os = response.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os)) {

            // 下载的字节范围
            int startByte, endByte, totalByte;
            if (request != null && request.getHeader("range") != null) {
                // 断点续传
                String[] range = request.getHeader("range").replaceAll("[^0-9\\-]", "").split("-");
                // 文件总大小
                totalByte = is.available();
                // 下载起始位置
                startByte = Integer.parseInt(range[0]);
                // 下载结束位置
                if (range.length > 1) {
                    endByte = Integer.parseInt(range[1]);
                } else {
                    endByte = totalByte - 1;
                }
                // 返回http状态
                response.setStatus(206);
            } else {
                // 正常下载
                // 文件总大小
                totalByte = is.available();
                // 下载起始位置
                startByte = 0;
                // 下载结束位置
                endByte = totalByte - 1;
                // 返回http状态
                response.setHeader("Accept-Ranges", "bytes");
                response.setStatus(200);
            }
            // 需要下载字节数
            int length = endByte - startByte + 1;

            //表明服务器支持分片加载
            response.setHeader("Accept-Ranges", "bytes");

            //Content-Range: bytes 0-65535/408244，表明此次返回的文件范围
            response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + totalByte);

            //告知浏览器这是一个字节流，浏览器处理字节流的默认方式就是下载
            response.setContentType("application/octet-stream");

            //表明该文件的所有字节大小
            response.setContentLength(length);

            //需要设置此属性，否则浏览器默认不会读取到响应头中的Accept-Ranges属性，因此会认为服务器端不支持分片，所以会直接全文下载
            response.setHeader("Access-Control-Expose-Headers", "Accept-Ranges,Content-Range");

            // 响应内容
            bis.skip(startByte);
            int len = 0;
            byte[] buff = new byte[1024 * 1024];
            while ((len = bis.read(buff, 0, buff.length)) != -1) {
                if (length <= len) {
                    bos.write(buff, 0, length);
                    break;
                } else {
                    length -= len;
                    bos.write(buff, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 图书封面下载接口
     * @param fileUUid
     * @param response
     * @throws IOException
     */
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
        url = remote_url_prefix + "file/avartar/" + fileUUid;
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
        //url = "http://124.71.166.37:9091/avartar/file/"+fileUUid;
        // url = "http://124.71.166.37:9091/file/avartar/"+fileUUid;
        url = remote_url_prefix + "file/avartar/"+fileUUid;
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
