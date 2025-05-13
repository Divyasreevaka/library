package com.example.lms.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{
}
