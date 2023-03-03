package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO {
    List<Book> getAll();
    Book getByIsbn(String isbn);
    void add(Book book);
    void updateBook(Book book);
    void removeByIsbn(String isbn);
}
