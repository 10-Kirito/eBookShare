package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.entity.Commits;
import com.example.ebookshare.mapper.CommitsMapper;
import com.example.ebookshare.service.ICommitsService;
import com.example.ebookshare.service.impl.CommitsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sel
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/commits")
public class CommitsController {
    @Resource
    ICommitsService commitsService;
    @Resource
    CommitsMapper commitsMapper;




    @GetMapping("/mankecommits")  //接口路径,多条件查询
    public Result findDeletedPage(@RequestParam String userid,
                                  @RequestParam String bookid,
                                  @RequestParam String text){
        Commits commits = new Commits();
        commits.setBookid(Integer.valueOf(bookid));
        commits.setText(text);
        commits.setUserid(Integer.valueOf(userid));
        commitsService.save(commits);
        return Result.success();
    }

    //打开某本书的页面，检索针对某本书的评论，从上到下，按照时间先后顺序
    //返回一个分页
    @GetMapping("/findcommits")  //接口路径,多条件查询
    public Result findBookCommits(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer bookid){
//        IPage<Commits> page = new Page<>(pageNum,pageSize);
//       QueryWrapper<Commits> queryWrapper = new QueryWrapper<>();
//       queryWrapper.eq("bookid",bookid);
//        queryWrapper.orderByDesc("time");

        Page<Commits> page = commitsService.findBookCommits(new Page<>(pageNum,pageSize),bookid);
        return Result.success(page);
    }
}
