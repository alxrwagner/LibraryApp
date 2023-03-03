package com.skypro.library.controller;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skypro")
public class RestControl {

    private final BookService bookService;

    public RestControl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }
    @PostMapping("/api/book")
    public void createBook(@RequestBody Book book) {
         bookService.add(book);
    }

    @PutMapping("/api/book")
    public void editBook(@RequestBody Book book){
        bookService.update(book);
    }

    @DeleteMapping("/api/book")
    public void deleteBook(@RequestParam String isbn){
        bookService.removeByIsbn(isbn);
    }
}
