package ru.gvozdilin.bibl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gvozdilin.bibl.entity.Readers;
import ru.gvozdilin.bibl.mapper.ReadersMapper;

import java.util.List;




public class ReadersDaoImpl implements ReadersDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReadersDaoImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Readers> findAll() {
       return jdbcTemplate.query("SELECT * from Readers;", new ReadersMapper());
    }
}
