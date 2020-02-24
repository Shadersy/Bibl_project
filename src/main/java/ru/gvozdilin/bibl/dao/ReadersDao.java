package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.entity.Readers;

import java.util.List;

public interface ReadersDao {
    List<Readers> findAll();
}
