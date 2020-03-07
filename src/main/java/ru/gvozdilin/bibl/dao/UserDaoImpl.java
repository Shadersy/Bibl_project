package ru.gvozdilin.bibl.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.bibl.entity.User;
import ru.gvozdilin.bibl.mapper.UserMapper;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    public final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", new UserMapper());
    }

    @Override
    public User getByLogin(String username) {
        String sql = "SELECT * FROM USER WHERE USERNAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }
}
