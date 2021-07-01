package com.firstspringboot.controller;

import com.firstspringboot.domain.Book;
import com.firstspringboot.impl.BookImpl;
import com.firstspringboot.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    public void test() {
        Book book = bookService.findBookByName("西方马克思主义译文集");
        System.out.println(book);
        System.out.println(book.getName());
    }

    @RequestMapping(value = "/searchBookByName")
    public String searchBookByName(ModelMap map, @RequestParam("name") String name) {
        try {
            Book book = bookService.findBookByName(name);
            System.out.println(name);
            if (book == null) {
                return "notFound";
            }
            map.addAttribute("book", book);
            map.addAttribute("url", book.getImage_url());
            System.out.println(book);
            return "search";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notFound";
    }

    @RequestMapping("/fuzzySearch")
    public String searchBookByName(ModelMap map, @RequestParam("name") String name,
                                   @RequestParam("judge") int judge) {
        try {
            List<Book> books = null;
            Long total = (long) 0;
            if (judge == 0) {
                books = bookService.fuzzyQueryByAuthor(name);
                total = bookService.fuzzyQueryByAuthorCount(name);
            } else {
                books = bookService.fuzzyQueryByName(name);
                total = bookService.fuzzyQueryByNameCount(name);
            }
            if (books == null || total == 0) {
                return "notFound";
            }
            System.out.println(name);
            System.out.println(books);
            System.out.println(total);
            map.addAttribute("name", name);
            map.addAttribute("total", total.toString());
            return "searchAll";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notFound";
    }
}