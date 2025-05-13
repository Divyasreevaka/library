package com.example.lms.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{
}
