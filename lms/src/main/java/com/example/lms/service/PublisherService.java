package com.example.lms.service;

import com.example.lms.entity.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lms.repo.PublisherRepo;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepo publisherRepo;
    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }
    public Publisher getPublisherById(int id) {
        return publisherRepo.findById(id).orElse(null);
    }
    public Publisher saveOrUpdatePublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }
    public void deletePublisherById(int id) {
        publisherRepo.findById(id).orElse(null);
        publisherRepo.deleteById(id);
    }
}

