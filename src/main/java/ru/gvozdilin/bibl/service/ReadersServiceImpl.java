package ru.gvozdilin.bibl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gvozdilin.bibl.dao.ReadersDao;
import ru.gvozdilin.bibl.entity.Readers;

import java.util.List;

@Service
public class ReadersServiceImpl implements ReadersService {

    @Autowired
    public ReadersDao readersDao;

    @Override
    public List<Readers> findAll() {
        return readersDao.findAll();
    }
}
