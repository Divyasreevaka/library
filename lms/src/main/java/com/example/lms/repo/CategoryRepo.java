package com.example.lms.repo;
import com.example.lms.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer>{
}
