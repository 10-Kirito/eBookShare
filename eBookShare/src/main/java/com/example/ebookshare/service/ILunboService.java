package com.example.ebookshare.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Lunbo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 前台轮播图 服务类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
public interface ILunboService extends IService<Lunbo> {
    Page<Lunbo> findpage(Page<Lunbo> page, String name);
}
