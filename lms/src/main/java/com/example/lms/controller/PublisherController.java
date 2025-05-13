package com.example.lms.controller;
import java.util.List;
import com.example.lms.entity.Publisher;
import com.example.lms.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers=publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable int id) {
        Publisher publisher=publisherService.getPublisherById(id);
        if(publisher==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publisher);
    }

    @PostMapping
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
        Publisher createPublisher=publisherService.saveOrUpdatePublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPublisher);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody Publisher publisher) {
        Publisher existingPublisher=publisherService.getPublisherById(id);
        if(existingPublisher==null) {
            return ResponseEntity.notFound().build();
        }
        publisher.setId(id);
        publisherService.saveOrUpdatePublisher(publisher);
        return ResponseEntity.ok(publisher);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisherById(id);
        return ResponseEntity.noContent().build();
    }
}
