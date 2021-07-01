package com.firstspringboot.service;

import com.firstspringboot.domain.Book;
import com.firstspringboot.impl.BookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookImpl bookImpl;

    @Autowired
    public BookService(BookImpl bookImpl) {
        this.bookImpl = bookImpl;
    }

    public Book findBookByName(String name) {
        return bookImpl.findBookByName(name);
    }

    public List<Book> fuzzyQueryByAuthor(String author) {
        return bookImpl.fuzzyQueryByAuthor(author);
    }

    public List<Book> fuzzyQueryByName(String name) {
        return bookImpl.fuzzyQueryByName(name);
    }

    public Long fuzzyQueryByAuthorCount(String author) {
        return bookImpl.fuzzyQueryByAuthorCount(author);
    }

    public Long fuzzyQueryByNameCount(String name) {
        return bookImpl.fuzzyQueryByNameCount(name);
    }
}
