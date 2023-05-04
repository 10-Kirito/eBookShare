package com.example.ebookshare.service.impl;

import com.example.ebookshare.entity.Users;
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

}
