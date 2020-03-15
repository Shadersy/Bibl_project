package ru.gvozdilin.bibl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinMapper implements RowMapper<Books> {
    @Override
    public Books mapRow(ResultSet resultSet, int i) throws SQLException {
        Books books = new Books();
        books.setId(resultSet.getInt("id"));
        books.setName(resultSet.getString("name"));
        books.setAutor(resultSet.getString("author"));
        books.setUserId(resultSet.getInt("userId"));
        books.setUsername(resultSet.getString("username"));
        return books;
    }
    }

