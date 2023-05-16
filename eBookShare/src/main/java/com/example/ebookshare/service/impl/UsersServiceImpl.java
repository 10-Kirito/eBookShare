package com.example.ebookshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ebookshare.common.Constants;
import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Admins;
import com.example.ebookshare.entity.Users;
import com.example.ebookshare.exception.ServiceException;
import com.example.ebookshare.mapper.UsersMapper;
import com.example.ebookshare.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sel
 * @since 2023-04-26
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public AdminDTO login(AdminDTO adminDTO) {
        Users one = getUserInfo(adminDTO);
        if(one != null) {
//            BeanUtil.copyProperties(one,userDTO,true);
//            //设置token
//            String token = TokenUtils.genToken(one.getUsername().toString(),one.getPassword());
//            userDTO.setToken(token);
            adminDTO.setId(String.valueOf(one.getId()));
            adminDTO.setEmail(String.valueOf(one.getEmail()));
            adminDTO.setAvatarurl(String.valueOf(one.getAvatarurl()));
            adminDTO.setUsername(String.valueOf(one.getUsername()));
            adminDTO.setPhone(String.valueOf(one.getPhone()));
            //后续这里需要设置token
            return adminDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
    private Users getUserInfo(AdminDTO adminDTO){
        QueryWrapper<Users> queryWrapper= new QueryWrapper<>();
        //校验输入的信息是否在数据库中，成功就返回用户信息以便于浏览器后面处理
        queryWrapper.eq("username", adminDTO.getUsername());
        queryWrapper.eq("password", adminDTO.getPassword());

        Users one;
        try{
            one = getOne(queryWrapper); //从数据库查询用户信息
        }catch (Exception e){
            //LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
