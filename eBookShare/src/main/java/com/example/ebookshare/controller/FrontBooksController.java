package com.example.ebookshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.APIResponse;
import com.example.ebookshare.common.APIStatusCode;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.*;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.mapper.RelationshipMapper;
import com.example.ebookshare.service.IBooksService;
import com.example.ebookshare.service.IRelationshipService;
import com.example.ebookshare.service.IUsersService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/FrontBooks")
public class FrontBooksController {
    @Resource
    private IBooksService booksService;

    @Resource
    private BooksMapper booksMapper;
    @Resource
    private IUsersService usersService;
    @Resource
    private IRelationshipService relationshipService;
    @Resource
    private RelationshipMapper relationshipMapper;

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
    //图书总榜单接口
    @GetMapping("/MostRanking")
    public APIResponse<List<Books>> MostRanking(){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("downloads").orderByDesc("likes").orderByDesc("favorites");
        return new APIResponse<>(booksService.list(queryWrapper),APIStatusCode.SUCCESS,"获取下载、喜欢、收藏最多的排序");
    }
    //下载总榜单接口
    @GetMapping("/MostDownload")
    public APIResponse<List<Books>> MostDownload(){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("downloads");
        return new APIResponse<>(booksService.list(queryWrapper),APIStatusCode.SUCCESS,"获取最受欢迎的图书");
    }
    //搜索接口模糊查询书籍名作者和出版社
    @GetMapping("/search")
    public APIResponse<List<Books>> search(@RequestParam(defaultValue = "") String bookinfo,
                                           @RequestParam(defaultValue = "") Integer userid,
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "12") Integer pageSize){
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        IPage<Books> page = new Page<>(pageNum,pageSize);
        queryWrapper.select("Distinct *")
                .like(StringUtils.isNotBlank(bookinfo),"bookname",bookinfo)
                .or()
                .like(StringUtils.isNotBlank(bookinfo),"author",bookinfo)
                .or()
                .like(StringUtils.isNotBlank(bookinfo),"publisher",bookinfo);
        IPage<Books> books=booksService.page(page,queryWrapper);
        //QueryWrapper<Relationship> newqueryWrapper=new QueryWrapper<>();
        Set<Integer> booksid = books.getRecords().stream().map(Books::getBookid).collect(Collectors.toSet());
        List<Relationship> relationships = relationshipMapper.selectList(Wrappers.lambdaQuery(Relationship.class).eq(Relationship::getUserid,userid).in(Relationship::getBookid,booksid));
        for (Relationship ship:relationships) {
            for (Books book:books.getRecords()){
                if(ship.getBookid()==book.getBookid())
                    if(ship.getIsfavour()==1)
                    book.setFavorites(-1);
                else if (ship.getIslike()==1)
                    book.setLikes(-1);
            }
        }
        return new APIResponse<>(books.getRecords(),APIStatusCode.SUCCESS,"返回搜索结果");
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
    //个人书架相关接口
    //拥有的书传入用户id和所要进行的操作，即要获取的是拥有的还是收藏的，传入String即为表中字段名,后两个为分页
    @GetMapping("/bookself")
    public APIResponse<List<Books>> BookSelf(@RequestParam(defaultValue = "1") Integer userid,
                         @RequestParam(defaultValue = "isowned") String operator,
                         @RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "12") Integer pageSize){
        IPage<Relationship> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Relationship> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        queryWrapper.eq(operator,1);
        queryWrapper.select("bookid");
        IPage<Relationship> bookid=relationshipService.page(page,queryWrapper);
        Set<Integer> booksid = bookid.getRecords().stream().map(Relationship::getBookid).collect(Collectors.toSet());
        List<Books> books = booksMapper.selectList(Wrappers.lambdaQuery(Books.class).in(Books::getBookid,booksid));
        return new APIResponse<>(books,APIStatusCode.SUCCESS,"返回个人书架");
    }
    @GetMapping("/score")
    public APIResponse<?> ScoreBook(@RequestParam(defaultValue = "1") Integer userid,
                                    @RequestParam(defaultValue = "60") Integer bookid,
                                    @RequestParam(defaultValue = "3") Integer score){
    UpdateWrapper<Relationship> updateWrapper=new UpdateWrapper<>();
    updateWrapper.eq("userid",userid).eq("bookid",bookid);
    updateWrapper.set("score",score);
    relationshipService.update(updateWrapper);
    return new APIResponse<>(GetAvgScore(bookid).getData(),APIStatusCode.SUCCESS,"返回得分");
    }
    @GetMapping("/GetAvgScore")
    public APIResponse<?> GetAvgScore(@RequestParam(defaultValue = "61") Integer bookid){
        QueryWrapper<Relationship> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("avg(score) as avgScore").eq("bookid",bookid);
        List<Map<String, Object>> rel=relationshipMapper.selectMaps(queryWrapper);
        rel.removeAll(Collections.singleton(null));
       if(rel==null||rel.isEmpty())
            return new APIResponse<>(null,APIStatusCode.SUCCESS,"返回得分");
       else
            return new APIResponse<>(rel.stream().findFirst().orElse(null).values(),APIStatusCode.SUCCESS,"返回得分");
    }
}
