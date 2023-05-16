package com.example.ebookshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.*;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.service.IBooksService;
import com.example.ebookshare.service.IUsersService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/FrontBooks")
public class FrontBooksController {
    @Resource
    private IBooksService booksService;

    @Resource
    private BooksMapper booksMapper;
    @Resource
    private IUsersService usersService;

    //分页查看所有书籍
    @GetMapping("/Centerbooklist/page")
    public IPage<Books> centerlist(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String bookname,
                                  @RequestParam(defaultValue = "") String author,
                                  @RequestParam(defaultValue = "") String publisher,
                                  @RequestParam(defaultValue = "") String isbn){
        IPage<Books> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
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
        return booksService.page(page,queryWrapper);
    }
    //按下载量排序输入选择当天最多下载量还是总下载量（表中字段）
    @GetMapping("/MostDownload")
    public List<Books> mostdownload(@RequestParam(defaultValue = "downloads") String order){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(order);
        return booksService.list(queryWrapper);

    }
    //搜索接口模糊查询书籍名作者和出版社
    @GetMapping("/search")
    public List<Books> search(@RequestParam(defaultValue = "") String bookinfo){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("Distinct *")
                .like(StringUtils.isNotBlank(bookinfo),"bookname",bookinfo)
                .or()
                .like(StringUtils.isNotBlank(bookinfo),"author",bookinfo)
                .or()
                .like(StringUtils.isNotBlank(bookinfo),"publisher",bookinfo);

        return booksService.list(queryWrapper);
    }
    //点赞接口
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/likes/{bookid}")
    public Result likesbook(@PathVariable int bookid){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        Books books = booksMapper.selectOne(queryWrapper);
        books.setLikes(books.getLikes()+1);
        booksService.update(books,queryWrapper);
        UpdateWrapper<Users> updateWrapper =new UpdateWrapper<>();
        updateWrapper.inSql("id","SELECT userID FROM bookUploader WHERE bookId = "+bookid);
        updateWrapper.setSql("points = points + 5");
        usersService.update(updateWrapper);
        return Result.success();
    }
}
