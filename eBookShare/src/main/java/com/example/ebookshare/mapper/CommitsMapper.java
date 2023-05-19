package com.example.ebookshare.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Commits;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sel
 * @since 2023-05-17
 */
@Mapper
public interface CommitsMapper extends BaseMapper<Commits> {

    Page<Commits> findBookCommits(Page<Object> page, Integer bookid);
}
