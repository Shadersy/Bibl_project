package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.entity.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
