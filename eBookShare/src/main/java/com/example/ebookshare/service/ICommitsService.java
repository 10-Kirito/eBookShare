package com.example.ebookshare.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Commits;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sel
 * @since 2023-05-17
 */
public interface ICommitsService extends IService<Commits> {

    Page<Commits> findBookCommits(Page<Object> page, Integer bookid);
}
