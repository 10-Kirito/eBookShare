package com.example.ebookshare.service;

import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sel
 * @since 2023-04-26
 */
public interface IUsersService extends IService<Users> {

    AdminDTO login(AdminDTO adminDTO);
}
