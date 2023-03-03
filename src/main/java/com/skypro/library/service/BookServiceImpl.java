package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.entity.Book;
import com.skypro.library.exceptions.BookException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    @Transactional
    public void add(Book book) {
        validateBook(book);
        bookDAO.add(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        if (isHasIsbn(book.getIsbn())) {
            bookDAO.updateBook(book);
        }
    }

    @Override
    @Transactional
    public void removeByIsbn(String isbn) {
        if (isHasIsbn(isbn)) {
            bookDAO.removeByIsbn(isbn);
        }
    }

    private boolean validateBook(Book book) {
        if (book.getIsbn() == null || book.getAuthor() == null || book.getTitle() == null || book.getYear() == null) {
            return false;
        }
        if (book.getYear() < 0) {
            return false;
        }

        String isbn = book.getIsbn().replaceAll("-", "");
        if (isbn.length() != 13) {
            return false;
        }

        if (!isbn.matches("[0-9]+")) {
            return false;
        }
        return validateIsbn(isbn);
    }

    private boolean validateIsbn(String isbn) {
        String[] numArr = isbn.split("");
        int sum = 0;
        int checkNum = 0;
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                sum += (Integer.parseInt(numArr[i]) * 3);
            } else {
                sum += Integer.parseInt(numArr[i]);
            }
        }
        checkNum = 10 - sum % 10;
        return checkNum == Integer.parseInt(numArr[12]);
    }

    private boolean isHasIsbn(String isbn) {
        Book checkedBook = bookDAO.getByIsbn(isbn);
        if (checkedBook == null) {
            throw new BookException("ISBN does not exist");
        }
        return true;
    }
}
