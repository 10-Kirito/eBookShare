package com.example.ebookshare.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Constants;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.entity.Relationship;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.mapper.RelationshipMapper;
import com.example.ebookshare.mapper.UsersMapper;
import com.example.ebookshare.service.IUsersService;
import com.example.ebookshare.service.impl.BooksServiceImpl;
import com.example.ebookshare.service.impl.RelationshipServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sel
 * @since 2023-04-26
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    IUsersService usersService;

    @Resource
    UsersMapper usersMapper;

    @Resource
    BooksServiceImpl booksService;

    @Resource
    BooksMapper booksMapper;

    @Resource
    RelationshipMapper relationshipMapper;

    @Resource
    RelationshipServiceImpl relationshipService;

    @GetMapping("/page")  //接口路径,多条件查询
    public IPage<Users> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String email,
                                 @RequestParam(defaultValue = "") String phone){
        IPage<Users> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        return usersService.page(page,queryWrapper);
    }

    @PostMapping("/save")
    public boolean save2(@RequestBody Users users){
        //新增或者更新
        return usersService.saveOrUpdate(users);
    }
    @PostMapping("/changepassword")
    public boolean changePassword(@RequestBody Users users){
        //用户修改密码
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",users.getUsername());
        queryWrapper.eq("id",users.getId());
        Users users1 = usersMapper.selectOne(queryWrapper);
        users1.setPassword(users.getPassword());
        return usersService.saveOrUpdate(users1);
    }
    @PostMapping
    public boolean save(@RequestBody Users users){
        //新增或者更新
        return usersService.saveOrUpdate(users);
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminDTO adminDTO){
        //新增或者更新
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            //校验字符串是否为空
            return  Result.error(Constants.CODE_400,"参数错误");
        }
        AdminDTO dto= usersService.login(adminDTO);
        return Result.success(dto);
    }
    @PostMapping("/register")
    public Result register(@RequestBody AdminDTO adminDTO){
        //新增或者更新
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();
        String email = adminDTO.getEmail();
        String phone = adminDTO.getPhone();

        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            //校验字符串是否为空
            return  Result.error(Constants.CODE_400,"参数错误");
        }
        //首先判断是否已经有用户名
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Users users2 = usersMapper.selectOne(queryWrapper);
        if(users2 != null){ //在数据库中已有相同用户名
            return Result.error(Constants.CODE_600,"用户名已存在");
        }



        Users users = new Users();
        users.setPhone(phone);
        users.setUsername(username);
        users.setPassword(password);
        users.setEmail(email);
        usersService.save(users);
        QueryWrapper<Users> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("username",username);
        Users users3 = usersMapper.selectOne(queryWrapper2);

        adminDTO.setUsername(String.valueOf(users3.getUsername()));
        adminDTO.setId(String.valueOf(users3.getId()));
        adminDTO.setEmail(String.valueOf(users3.getEmail()));
        adminDTO.setAvatarurl(String.valueOf(users3.getAvatarurl()));
        adminDTO.setPhone(String.valueOf(users3.getPhone()));

        return Result.success(adminDTO);
    }
    //导出用户信息
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //需要筛选出没有被删除的书籍
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        List<Users> list = usersService.list(queryWrapper);
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
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        //输出文件名称
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

    @DeleteMapping("/{id}")
    public Result trueDeleteBooks(@PathVariable Integer id){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Users users = usersMapper.selectOne(queryWrapper);

        usersMapper.deleteById(users);
        return Result.success();
    }


    @GetMapping("/{username}")
    public Result getUser(@PathVariable String username){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Users users = usersMapper.selectOne(queryWrapper);

        return Result.success(users);
    }


    @GetMapping("/pointbuybook")
    public Result pointbuybook(@RequestParam(defaultValue = "0") Integer userid,
                           @RequestParam(defaultValue = "0") Integer bookid) {
        if (userid == 0 || bookid == 0) {
            return Result.error("300", "传入数据错误");
        }
        QueryWrapper<Books> booksQueryWrapper = new QueryWrapper<>();
        booksQueryWrapper.eq("bookid",bookid);
        Books books = booksMapper.selectOne(booksQueryWrapper);
        if (books == null){
            return Result.error("400","找不到书籍信息");
        }
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("id",userid);
        Users users = usersMapper.selectOne(usersQueryWrapper);
        if (users == null){
            return Result.error("500","找不到用户信息");
        }

        //判断是否有重复的表单
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookid",bookid);
        queryWrapper.eq("userid",userid);
        Relationship relationship = relationshipMapper.selectOne(queryWrapper);

        if (relationship != null){
        //找到了对应的现成数据
            if (relationship.getIsowned()==1){
                //用户已购买书籍
                return Result.success("10","用户已购买书籍");
            }
        }

        //判断是否有免费下载次数
        if(users.getFreedownload()>0){

            if (relationship != null){
//                找到了对应的现成数据
                if (relationship.getIsowned()==1){
                    //用户已购买书籍
                    return Result.success("10","用户已购买书籍");
                }
                users.setFreedownload(users.getFreedownload()-1);
                usersService.saveOrUpdate(users,usersQueryWrapper);


                Relationship relationship1 = relationship;
                relationship1.setIsowned(1);
                QueryWrapper<Relationship> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("bookid",bookid);
                queryWrapper1.eq("userid",userid);
                relationshipService.remove(queryWrapper1);

                relationshipService.save(relationship1);

//                relationshipService.saveOrUpdate(relationship);
            }
            else {
//                找不到对应的现成数据,就创建新的表项
                Relationship relationship1 = new Relationship();
                relationship1.setIsowned(1);
                relationship1.setBookid(bookid);
                relationship1.setUserid(userid);
                relationshipService.save(relationship1);


                users.setFreedownload(users.getFreedownload()-1);
                usersService.saveOrUpdate(users,usersQueryWrapper);

            }
            return Result.success("200","使用免费次数购买成功");

        }
        //如果没有免费下载次数,查看积分并扣取
        //默认一次扣取5积分
        if(users.getPoints()>=5){


            if (relationship != null){
//                找到了对应的现成数据
                if (relationship.getIsowned()==1){
                    //用户已购买书籍
                    return Result.success("10","用户已购买书籍");
                }
                users.setPoints(users.getPoints()-5);
                usersService.saveOrUpdate(users,usersQueryWrapper);



                Relationship relationship1 = relationship;
                relationship1.setIsowned(1);
                QueryWrapper<Relationship> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("bookid",bookid);
                queryWrapper1.eq("userid",userid);
                relationshipService.remove(queryWrapper1);

                relationshipService.save(relationship1);


//                relationship.setIsowned(1);
                // my-batisplus只支持单主键更新
//                relationshipService.saveOrUpdate(relationship);
            }
            else {
                //找不到对应的现成数据,就创建新的表项
                Relationship relationship1 = new Relationship();
                relationship1.setIsowned(1);
                relationship1.setBookid(bookid);
                relationship1.setUserid(userid);
                relationshipService.save(relationship1);

                // 扣取积分
                users.setPoints(users.getPoints()-5);
                usersService.saveOrUpdate(users,usersQueryWrapper);
            }
            return Result.success("200","使用积分购买成功");
        }

        //如果积分不够
        return Result.error("600","积分不足，购买失败");

    }
    @GetMapping("/ifexistfree")
    public Result ifExistFreeBuyPoints(@RequestParam(defaultValue = "0") Integer userid) {
        if (userid == 0) {
            return Result.error("300", "传入数据错误");
        }
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("id",userid);
        Users users = usersMapper.selectOne(usersQueryWrapper);
        if (users == null){
            return Result.error("500","找不到用户信息");
        }

        //判断是否有免费下载次数
        if(users.getFreedownload()>0){
            return Result.success("200","该用户拥有免费购买次数");
        }
        else {
            return Result.error("600","该用户没有免费购买次数");
        }


    }

}
