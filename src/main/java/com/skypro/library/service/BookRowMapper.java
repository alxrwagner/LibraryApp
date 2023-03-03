package com.skypro.library.service;

import com.skypro.library.entity.Book;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book newBook = new Book();
        newBook.setTitle(rs.getString("title"));
        newBook.setAuthor(rs.getString("author"));
        newBook.setYear(rs.getInt("year"));
        newBook.setIsbn(rs.getString("isbn"));
        return newBook;
    }
}
