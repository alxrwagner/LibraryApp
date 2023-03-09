package com.skypro.library.controller;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("skypro")
public class SpringMVCController {
    private final BookService bookService;

    public SpringMVCController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/web")
    public String showBooks(Model model){
        model.addAttribute("books", bookService.getAll());
        return "dashboard";
    }
}
