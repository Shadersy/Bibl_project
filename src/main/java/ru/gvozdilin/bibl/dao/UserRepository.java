package ru.gvozdilin.bibl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.bibl.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
