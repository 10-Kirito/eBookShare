package com.example.ebookshare.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    List<Books> getTopTenBooks(Integer userid);
    List<Books> randomBooks(Integer number);

    List<Books> downloadBooks(Integer userid);

    List<Books> randomBooksWithUser(Integer number, Integer userid);

    Page<Books> findPages(Page<Books> objectPage, Integer userid, String operator);
}
