package com.example.ebookshare.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Constants;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Admins;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.service.IAdminsService;
import com.example.ebookshare.service.IBooksService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sel
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/books")
public class BooksController {
    @Resource
    private IBooksService booksService;



    @GetMapping("/bookname/{bookname}")
    public Result findOne(@PathVariable String bookname){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookname",bookname);
        return Result.success(booksService.getOne(queryWrapper));
    }

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Books books){
        //新增或者更新
        return booksService.saveOrUpdate(books);
    }

    //删除
    @DeleteMapping("/{id}")
    public boolean  delete(@PathVariable Integer id){
        return booksService.removeById(id);
    }


    @PostMapping("/del/batch")
    public boolean  deleteBatch(@RequestBody List<Integer> ids){
        return booksService.removeByIds(ids);
    }


    //查询所有
    @GetMapping
    public List<Books> findAll(){
        return booksService.list();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Books findOne(@PathVariable Integer id){
        return booksService.getById(id);
    }

    //分页查询
    @GetMapping("/page")  //接口路径,多条件查询
    public IPage<Books> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
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

        return booksService.page(page,queryWrapper);
    }
}
