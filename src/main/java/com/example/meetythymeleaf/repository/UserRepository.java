package com.example.meetythymeleaf.repository;


import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> getUsersByIdIn(List<Long> ids);
    List<User> findUsersByInterests_Name(String interestName);
    Optional<List<User>> findUsersByFullNameContaining(String fullName);

}
