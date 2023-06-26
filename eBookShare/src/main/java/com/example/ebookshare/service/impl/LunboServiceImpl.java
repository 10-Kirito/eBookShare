package com.example.ebookshare.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Lunbo;
import com.example.ebookshare.mapper.LunboMapper;
import com.example.ebookshare.service.ILunboService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 前台轮播图 服务实现类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@Service
public class LunboServiceImpl extends ServiceImpl<LunboMapper, Lunbo> implements ILunboService {
    @Resource
    private LunboMapper lunboMapper;

    @Override
    public Page<Lunbo> findpage(Page<Lunbo> page, String name) {
        return lunboMapper.findPage(page,name);
    }
}
