package com.example.ebookshare.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Books;
import com.example.ebookshare.mapper.BooksMapper;
import com.example.ebookshare.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
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
    public List<Books> getTopTenBooks(Integer userid) {
        return booksMapper.getTopTenBooks(userid);
    }

    @Override
    public List<Books> downloadBooks(Integer userid) {
        return booksMapper.downloadBooks(userid);
    }

    @Override
    public List<Books> randomBooksWithUser(Integer number, Integer userid) {
        return booksMapper.randomBooksWithUser(number,userid);
    }

    @Override
    public Page<Books> findPages(Page<Books> objectPage, Integer userid, String operator) {
        return booksMapper.findPages(objectPage,userid,operator);
    }
}
