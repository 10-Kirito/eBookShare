package com.example.ebookshare.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Commits;
import com.example.ebookshare.mapper.CommitsMapper;
import com.example.ebookshare.service.ICommitsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sel
 * @since 2023-05-17
 */
@Service
public class CommitsServiceImpl extends ServiceImpl<CommitsMapper, Commits> implements ICommitsService {

    @Resource
    private CommitsMapper commitsMapper;

    public Page<Commits> findBookCommits(Page<Object> page, Integer bookid) {
        return commitsMapper.findBookCommits(page,bookid);
    }
}
