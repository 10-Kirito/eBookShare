package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.BookUploader;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.mapper.BookUploaderMapper;
import com.example.ebookshare.service.impl.BookUploaderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sel
 * @since 2023-05-16
 */
@RestController
@RequestMapping("/book-uploader")
public class BookUploaderController {
    @Resource
    BookUploaderMapper bookUploaderMapper;
    @Resource
    BookUploaderServiceImpl bookUploaderService;

    //查看书籍的上传者
    @GetMapping("/uploader/{bookid}")
    public BookUploader favoritesbook(@PathVariable String bookid){
        QueryWrapper<BookUploader> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        BookUploader bookUploader = bookUploaderMapper.selectOne(queryWrapper);
        return bookUploader;
    }
}
