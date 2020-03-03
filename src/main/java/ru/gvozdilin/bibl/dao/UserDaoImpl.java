package ru.gvozdilin.bibl.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gvozdilin.bibl.entity.User;
import ru.gvozdilin.bibl.mapper.UserMapper;

import java.util.List;

public class UserDaoImpl implements UserDao {


    public final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getByLogin() {
        return jdbcTemplate.query("SELECT * FROM user WHERE username = ?", new UserMapper());
    }
}