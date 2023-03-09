package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookRowMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate template;

    public BookDAOImpl(@Lazy JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Book> getAll() {
        return template.query("SELECT * FROM book", new BookRowMapper());
    }

    @Override
    public Book getByIsbn(String isbn) {
        return template.query("SELECT * FROM book WHERE isbn = ?",
                new Object[]{isbn},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    @Override
    public void add(Book book) {
        template.update("INSERT INTO book VALUES (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book) {
        template.update("UPDATE book SET title = ?, author = ?, year = ? WHERE isbn = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void removeByIsbn(String isbn) {
        template.update("DELETE FROM book WHERE isbn = ?", isbn);
    }
}
