package com.example.meetythymeleaf.repository;


import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.model.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByIdIn( List<Long> ids);
}
