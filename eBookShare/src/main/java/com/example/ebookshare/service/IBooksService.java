package com.example.ebookshare.service;

import com.example.ebookshare.common.APIResponse;
import com.example.ebookshare.entity.Books;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sel
 * @since 2023-04-24
 */
public interface IBooksService extends IService<Books> {

    List<Books> randomBooks(Integer number);

    List<Books> getTopTenBooks(Integer userid);

    List<Books> downloadBooks(Integer userid);

    List<Books> randomBooksWithUser(@Param("number") Integer number, @Param("userid") Integer userid);
}
