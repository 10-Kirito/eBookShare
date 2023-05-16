package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Relationship;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.service.impl.RelationshipServiceImpl;
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
 * @since 2023-05-16
 */
@RestController
@RequestMapping("/relationship")
public class RelationshipController {
    @Resource
    RelationshipServiceImpl relationshipService;


    // 购买书籍接口
    @GetMapping("/buybook")  //接口路径,多条件查询
    public Result buybook(@RequestParam Integer bookid,
                           @RequestParam Integer userid){
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        queryWrapper.eq("userid",userid);
        //先查找是否已有 数据项，如果已有，只需要修改状态位即可
        Relationship relationship = relationshipService.getOne(queryWrapper);
        if (relationship != null){ //数据库中已有 用户和该书的关系数据
            //修改状态位，随后返回
            relationshipService.remove(queryWrapper);
            relationship.setIsowned(1);
            relationshipService.save(relationship);
        }
        else{//原先数据库中没有数据，新增一个
            Relationship relationship1 = new Relationship();
            relationship1.setBookid(bookid);
            relationship1.setUserid(userid);
            relationship1.setIsowned(1);
            relationship1.setIsfavour(0);
            relationship1.setIsuploader(0);
            relationshipService.save(relationship1);
        }
        return Result.success();
    }

    //收藏书籍接口
    @GetMapping("/favourbook")  //接口路径,多条件查询
    public Result favourbook(@RequestParam Integer bookid,
                           @RequestParam Integer userid){
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        queryWrapper.eq("userid",userid);
        //先查找是否已有 数据项，如果已有，只需要修改状态位即可
        Relationship relationship = relationshipService.getOne(queryWrapper);
        if (relationship != null){ //数据库中已有 用户和该书的关系数据
            //修改状态位，随后返回
            relationshipService.remove(queryWrapper);
            relationship.setIsfavour(1);
            relationshipService.save(relationship);
        }
        else{//原先数据库中没有数据，新增一个
            Relationship relationship1 = new Relationship();
            relationship1.setBookid(bookid);
            relationship1.setUserid(userid);
            relationship1.setIsowned(0);
            relationship1.setIsfavour(1);
            relationship1.setIsuploader(0);
            relationshipService.save(relationship1);
        }
        return Result.success();
    }
}
