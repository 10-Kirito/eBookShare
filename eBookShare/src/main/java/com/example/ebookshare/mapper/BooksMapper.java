package com.example.ebookshare.mapper;

import com.example.ebookshare.common.APIResponse;
import com.example.ebookshare.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Book;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sel
 * @since 2023-04-24
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    List<Books> randomBooks(Integer number);
}
