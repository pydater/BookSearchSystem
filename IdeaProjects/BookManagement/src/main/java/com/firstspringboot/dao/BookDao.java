package com.firstspringboot.dao;

import com.firstspringboot.domain.Book;

import java.util.List;

public interface BookDao {
    public Book findBookByName(String name);    //通过名字查找数据
    public List<Book> fuzzyQueryByAuthor(String author);
    public List<Book> fuzzyQueryByName(String name);
    public Long fuzzyQueryByAuthorCount(String author);
    public Long fuzzyQueryByNameCount(String name);
}
