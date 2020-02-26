package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.service.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.mapper.BooksMapper;

import java.util.List;

public class BooksDaoImpl implements BooksDao {


    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Books> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", new BooksMapper());
    }

    @Override
    public List<Books> sortByAuthor() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY author", new BooksMapper());
    }

    @Override
    public List<Books> sortByName() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY name", new BooksMapper());
    }


    public void deleteBooks(Integer id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public void addBooks(String name, String author) {
        jdbcTemplate.update("INSERT INTO books (name, author) VALUES (?, ?)", name, author);
    }
}