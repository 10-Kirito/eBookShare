package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.service.IUsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @PostMapping
    public boolean save(@RequestBody Users users){
        //新增或者更新
        return usersService.saveOrUpdate(users);
    }
}
