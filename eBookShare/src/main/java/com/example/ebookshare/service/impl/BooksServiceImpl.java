package com.example.ebookshare.service.impl;

import com.example.ebookshare.entity.Books;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sel
 * @since 2023-04-24
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {

    @Autowired
    BooksMapper booksMapper;

    @Override
    public List<Books> randomBooks(Integer number) {
        return booksMapper.randomBooks(number);
    }
    @Override
    public List<Books> getTopTenBooks() {
        return booksMapper.getTopTenBooks();
    }

    @Override
    public List<Books> downloadBooks() {
        return booksMapper.downloadBooks();
    }
}
