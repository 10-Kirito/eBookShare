package com.example.ebookshare.service;

import com.example.ebookshare.common.APIResponse;
import com.example.ebookshare.entity.Books;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
