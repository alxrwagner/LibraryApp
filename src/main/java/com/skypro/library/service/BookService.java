package com.skypro.library.service;

import com.skypro.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    void add(Book book);
    void update(Book book);
    void removeByIsbn(String isbn);
}
