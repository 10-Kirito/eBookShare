package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.entity.Relationship;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.mapper.RelationshipMapper;
import com.example.ebookshare.service.impl.BooksServiceImpl;
import com.example.ebookshare.service.impl.RelationshipServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    RelationshipMapper relationshipMapper;
    @Resource
    BooksServiceImpl booksService;
    @Resource
    BooksMapper booksMapper;

    //当用户点击进入某本书的时候，查找点赞，收藏和评分关系
    @GetMapping("/findrelation")  //接口路径,多条件查询
    public Result findRelationship(@RequestParam Integer bookid,
                                         @RequestParam Integer userid){

        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        queryWrapper.eq("userid",userid);
        Relationship relationship = relationshipMapper.selectOne(queryWrapper);
        if(relationship == null){
            return Result.error("400","未查询到relationship数据");
        }
        return Result.success(relationship);
    }



    //根据某个用户，查找其购买的书目分页信息
    @GetMapping("/selectboughtbook")  //接口路径,多条件查询
    public IPage<Books> selectboughtbook(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam Integer userid){
        IPage<Books> page;
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        queryWrapper.eq("isowned",1);

        List<Books> result = new ArrayList<>();
        List<Relationship> relationships = relationshipService.list(queryWrapper);
        for (Relationship o : relationships) {
            QueryWrapper<Books> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("bookid",o.getBookid());

            Books temp = booksMapper.selectOne(queryWrapper1);
            result.add(temp);
        }
        page = listToPage(result,pageNum,pageSize);

        return page;
    }


    //根据某个用户，查找其收藏的书目分页信息
    @GetMapping("/selectfavour")  //接口路径,多条件查询
    public IPage<Books> selectfavourbook(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer userid){
        IPage<Books> page;
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        queryWrapper.eq("isfavour",1);

        List<Books> result = new ArrayList<>();
        List<Relationship> relationships = relationshipService.list(queryWrapper);
        for (Relationship o : relationships) {
            QueryWrapper<Books> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("bookid",o.getBookid());

            Books temp = booksMapper.selectOne(queryWrapper1);
            result.add(temp);
        }
        page = listToPage(result,pageNum,pageSize);

        return page;
    }
    public static IPage listToPage(List list, int pageNum, int pageSize){
        List pageList = new ArrayList<>();
        int curIdx = pageNum > 1 ? (pageNum - 1) * pageSize : 0;
        for (int i = 0; i < pageSize && curIdx + i < list.size(); i++) {
            pageList.add(list.get(curIdx + i));
        }
        IPage page = new Page<>(pageNum, pageSize);
        page.setRecords(pageList);
        page.setTotal(list.size());
        return page;
    }
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
