package com.example.ebookshare.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ebookshare.common.Constants;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Admins;
import org.springframework.web.bind.annotation.*;
import com.example.ebookshare.service.IAdminsService;


import javax.annotation.Resource;
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
@RequestMapping("/admins")
public class AdminsController {


        @Resource
        private IAdminsService adminsService;


        @PostMapping("/login")
        public Result login(@RequestBody AdminDTO adminDTO){
            //新增或者更新
            String username = adminDTO.getUsername();
            String password = adminDTO.getPassword();
            if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
                //校验字符串是否是空
                return  Result.error(Constants.CODE_400,"参数错误");
            }
            AdminDTO dto= adminsService.login(adminDTO);
            String a = "";
            return Result.success(dto);
        }
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){
        QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(adminsService.getOne(queryWrapper));
    }

        //新增或更新
        @PostMapping
        public boolean save(@RequestBody Admins admins){
            //新增或者更新
            return adminsService.saveOrUpdate(admins);
        }

        //删除
        @DeleteMapping("/{id}")
        public boolean  delete(@PathVariable Integer id){
            return adminsService.removeById(id);
        }


        @PostMapping("/del/batch")
        public boolean  deleteBatch(@RequestBody List<Integer> ids){
            return adminsService.removeByIds(ids);
        }


        //查询所有
        @GetMapping
        public List<Admins> findAll(){
            return adminsService.list();
        }

        //根据id查询
        @GetMapping("/{id}")
        public Admins findOne(@PathVariable Integer id){
            return adminsService.getById(id);
        }

        //分页查询
//    @GetMapping("/page")
//    public Page<Admins> findPage(@RequestParam Interger pageNum,
//        @RequestParam Interger pageSize){
//        QueryWrapper<department> queryWrapper = new QueryWrapper<>();
//        //queryWrapper.orderByDesc("id");
//        return adminsService.page(new Page<>(pageNum,pageSize));
//    }
    }
