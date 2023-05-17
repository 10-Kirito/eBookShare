package com.example.ebookshare.mapper;

import com.example.ebookshare.common.APIResponse;
import com.example.ebookshare.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("SELECT * FROM books ORDER BY (likes + favorites + downloads) DESC LIMIT 10")
    List<Books> getTopTenBooks();
    List<Books> randomBooks(Integer number);
}
