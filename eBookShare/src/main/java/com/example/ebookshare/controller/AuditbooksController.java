package com.example.ebookshare.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Auditbooks;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.mapper.AuditbooksMapper;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.service.IAuditbooksService;
import com.example.ebookshare.service.IBooksService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sel
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/auditbooks")
public class AuditbooksController {
    @Resource
    private IAuditbooksService auditbooksService;

    @Resource
    private IBooksService booksService;
    @Resource
    private BooksMapper booksMapper;
    @Value("${auditbooks.upload.path}")//需要审核的书籍目录
    private String auditbooksPAth;
    @Value("${auditbookpic.upload.path}")//需要审核的书籍目录
    private String auditbookspicPath;


    @Value("${books.upload.path}")//书籍目录
    private String booksPath;

    @Value("${bookpic.upload.path}")//书籍目录
    private String bookpicPath;

    @Resource
    private AuditbooksMapper auditbooksMapper;

    //新增或更新
    @PostMapping("/save")
    public boolean save2(@RequestBody Auditbooks books){
        //新增或者更新
        return auditbooksService.saveOrUpdate(books);
    }
    @PostMapping
    public boolean save(@RequestBody Auditbooks books){
        //新增或者更新
        return auditbooksService.saveOrUpdate(books);
    }

    //删除
//    @DeleteMapping("/{id}")
//    public boolean  delete(@PathVariable Integer id){
//        return booksService.removeById(id);
//    }




    @DeleteMapping("/audit/{bookid}")
    public Result  recoverBooks(@PathVariable String bookid){
        Auditbooks books = auditbooksMapper.selectById(bookid);
        //查找数据库
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        Auditbooks auditbooks;
        auditbooks = auditbooksService.getOne(queryWrapper);
        //1  转移文件目录


        //       #源文件路径
        File startFile=new File(auditbooks.getUrl());
        //#目的目录路径
        File endDirection=new File(booksPath);
        //#如果目的目录路径不存在，则进行创建
        if(!endDirection.exists()) {
            endDirection.mkdirs();
        }
        //#目的文件路径=目的目录路径+源文件名称
        File endFile=new File(endDirection+ File.separator+ startFile.getName());
        try {
        //	#调用File类的核心方法renameTo
            if (startFile.renameTo(endFile)) {
                System.out.println("文件移动成功！目标路径：{"+endFile.getAbsolutePath()+"}");
            } else {
                System.out.println("文件移动失败！起始路径：{"+startFile.getAbsolutePath()+"}");
            }
        }catch(Exception e) {
            System.out.println("文件移动出现异常！起始路径：{"+startFile.getAbsolutePath()+"}");
        }


        //2  转移数据库信息
        auditbooksMapper.deleteById(auditbooks);
        Books books1 = new Books(auditbooks);
        booksMapper.insert(books1);
        return Result.success();
    }
    @DeleteMapping("/cancel")
    public Result  cancel(@RequestParam String bookid,@RequestParam String suggestions){
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        Auditbooks auditbooks = auditbooksService.getOne(queryWrapper);
        auditbooks.setSuggestions(suggestions);
        //设置标志位，表示这个记录已经被审核过了
        auditbooks.setIsdelete(true);
        auditbooks.setEnable(false);
        auditbooksService.saveOrUpdate(auditbooks);
        return Result.success();
    }
    //查询所有
    @GetMapping
    public List<Auditbooks> findAll(){
        return auditbooksService.list();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Auditbooks findOne(@PathVariable Integer id){
        return auditbooksService.getById(id);
    }

    //分页查询
    @GetMapping("/page")  //接口路径,多条件查询
    public IPage<Auditbooks> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String bookname,
                                 @RequestParam(defaultValue = "") String author,
                                 @RequestParam(defaultValue = "") String publisher,
                                 @RequestParam(defaultValue = "") String isbn){
        IPage<Auditbooks> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        if(!"".equals(bookname)){
            queryWrapper.like("bookname",bookname);
        }
        if(!"".equals(author)){
            queryWrapper.like("author",author);
        }
        if(!"".equals(publisher)){
            queryWrapper.like("publisher",publisher);
        }
        if(!"".equals(isbn)){
            queryWrapper.like("isbn",isbn);
        }
        //筛选未删除的书籍
        queryWrapper.like("isdelete",0);
        queryWrapper.like("enable",1);
        return auditbooksService.page(page,queryWrapper);
    }
    @GetMapping("/deletedpage")  //接口路径,多条件查询
    public IPage<Auditbooks> findDeletedPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(defaultValue = "") String bookname,
                                        @RequestParam(defaultValue = "") String author,
                                        @RequestParam(defaultValue = "") String publisher,
                                        @RequestParam(defaultValue = "") String isbn){
        IPage<Auditbooks> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        if(!"".equals(bookname)){
            queryWrapper.like("bookname",bookname);
        }
        if(!"".equals(author)){
            queryWrapper.like("author",author);
        }
        if(!"".equals(publisher)){
            queryWrapper.like("publisher",publisher);
        }
        if(!"".equals(isbn)){
            queryWrapper.like("isbn",isbn);
        }
        //筛选未删除的书籍
        queryWrapper.like("enable",0);
        return auditbooksService.page(page,queryWrapper);
    }
    @DeleteMapping("/{bookid}")
    public Result  delete(@PathVariable Integer bookid){
//        Auditbooks books = auditbooksMapper.selectById(bookid);
//        books.setIsdelete(true);
//        books.setEnable(false);
//        auditbooksMapper.updateById(books);
//        return Result.success();
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        Auditbooks books = auditbooksMapper.selectOne(queryWrapper);
        File uploadFile = new File(books.getUrl());
        //先删除实体文件，再删除数据库记录
        uploadFile.delete();
        auditbooksMapper.deleteById(books);

        return Result.success();
    }
    @DeleteMapping("/recover/{bookid}")
    public Result  recoverBooks(@PathVariable Integer bookid){
        Auditbooks books = auditbooksMapper.selectById(bookid);
        books.setIsdelete(false);
        books.setEnable(true);
        auditbooksMapper.updateById(books);
        return Result.success();
    }
    @DeleteMapping("/truedelete/{bookid}")
    public Result  trueDeleteBooks(@PathVariable Integer bookid){
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        Auditbooks books = auditbooksMapper.selectOne(queryWrapper);



        File uploadFile = new File(books.getUrl());
        //先删除实体文件，再删除数据库记录
        uploadFile.delete();
        auditbooksMapper.deleteById(books);

        return Result.success();
    }
    @PostMapping("/upload")
    public String  upload(@RequestParam MultipartFile file) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);
        long size = file.getSize();

        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(auditbooksPAth + fileUUid);

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
        Auditbooks dbFiles = getFileByMd5(md5);
        if (dbFiles != null){
            url = dbFiles.getUrl();
            //删除之前已存在的重复文件，以便于上传最新版文件
            uploadFile.delete();
        }else {
            //数据库不存在重复的文件
            //把获取到的文件存储到磁盘目录
//            url = "http://localhost:9090/file/"+fileUUid;
            url = auditbooksPAth+fileUUid;
            Auditbooks saveFile = new Auditbooks();
            saveFile.setFilename(orginalFilename);
            saveFile.setType(type);
            saveFile.setSize(size/1024); //最后存储单位是kb
            saveFile.setUrl(url);
            saveFile.setMd5(md5);
            saveFile.setEnable(true);
            saveFile.setIsdelete(false);
            auditbooksMapper.insert(saveFile);
        }
        //获取文件url
        //把获取到的文件存储到磁盘目录中

        //文件路径
        //存储数据库

        return url; //文件下载链接
        //上传成功后返回url
    }

    @PostMapping("/uploadpic")
    public String  uploadpic(@RequestParam MultipartFile file) throws IOException {
    //逻辑：因为没有参考的数据，所以查找时间最近的数据，但是没有图片的数据，将此图片直接添加到其中
        //向目录添加文件
        String orginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(orginalFilename);
        long size = file.getSize();
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT +type;
        File uploadFile = new File(auditbookspicPath + fileUUid);
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
        url = auditbookspicPath+fileUUid;

        //向数据库添加数据
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("releasetime");
        queryWrapper.isNull("coverimage");
        Auditbooks auditbooks = auditbooksMapper.selectOne(queryWrapper);
        auditbooks.setCoverimage(url);
        auditbooksService.saveOrUpdate(auditbooks);


        return url; //文件下载链接
        //上传成功后返回url
    }

    private Auditbooks getFileByMd5(String md5){
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Auditbooks> filesList = auditbooksMapper.selectList(queryWrapper);
        //获取第一个，因为有可能重名，多条记录
        return filesList.size() == 0 ? null: filesList.get(0);
    }
    @GetMapping("/addbookinfo")  //接口路径,多条件查询
    public Result findDeletedPage(@RequestParam(defaultValue = "") String filename,
                                  @RequestParam(defaultValue = "") String bookname,
                                  @RequestParam(defaultValue = "") String author,
                                  @RequestParam(defaultValue = "") String publisher,
                                  @RequestParam(defaultValue = "") String isbn,
                                  @RequestParam(defaultValue = "") String description,
                                  @RequestParam(defaultValue = "") String category){
//        IPage<Books> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        if(!"".equals(bookname)){
            queryWrapper.like("filename",filename);
        }
        //为了避免检索的时候可能出现问题，逆向进行查找
        queryWrapper.orderByDesc("bookid");
        Auditbooks books = auditbooksService.getOne(queryWrapper);
        if(books != null)
        {
            books.setBookname(bookname);
            books.setAuthor(author);
            books.setPublisher(publisher);
            books.setIsbn(isbn);
            books.setDescription(description);
            books.setCategory(category);
            auditbooksService.updateById(books);
        }
        //对找到的数据进行设置
        return Result.success();
    }
    @GetMapping("/deletedexport")
    public void deletedexport(HttpServletResponse response) throws Exception{
        //需要筛选出没有被删除的书籍
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable",0);
        List<Auditbooks> list = auditbooksService.list(queryWrapper);
        //从数据库中查询出所有数据
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //在内存操作，写出到浏览器

        //自定义标签别名
//        writer.addHeaderAlias("deptid","学院号");
//        writer.addHeaderAlias("deptname","学院名称");
//        writer.addHeaderAlias("address","学院地址");
//        writer.addHeaderAlias("phonecode","学院号");

        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("书籍信息","UTF-8");
        //输出文件名称
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

    //导出书籍
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //需要筛选出没有被删除的书籍
        QueryWrapper<Auditbooks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isdelete",0);
        List<Auditbooks> list = auditbooksService.list(queryWrapper);
        //从数据库中查询出所有数据
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //在内存操作，写出到浏览器

        //自定义标签别名
//        writer.addHeaderAlias("deptid","学院号");
//        writer.addHeaderAlias("deptname","学院名称");
//        writer.addHeaderAlias("address","学院地址");
//        writer.addHeaderAlias("phonecode","学院号");

        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("书籍信息","UTF-8");
        //输出文件名称
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
}
