package com.example.ebookshare.service;

import com.example.ebookshare.controller.dto.AdminDTO;
import com.example.ebookshare.entity.Admins;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sel
 * @since 2023-04-24
 */
public interface IAdminsService extends IService<Admins> {

    AdminDTO login(AdminDTO adminDTO);

    AdminDTO register(AdminDTO adminDTO);
}
