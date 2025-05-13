package com.example.lms.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Publisher;

public interface PublisherRepo extends JpaRepository<Publisher, Integer>{
}
