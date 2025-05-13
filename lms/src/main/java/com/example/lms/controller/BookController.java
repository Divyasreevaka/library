package com.example.lms.controller;
import java.util.ArrayList;
import java.util.List;

import com.example.lms.entity.Author;
import com.example.lms.entity.Book;
import com.example.lms.entity.Category;
import com.example.lms.entity.Publisher;
import com.example.lms.service.AuthorService;
import com.example.lms.service.BookService;
import com.example.lms.service.CategoryService;
import com.example.lms.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublisherService publisherService;
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books=bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book=bookService.getBookById(id);
        if(book==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        List<Author> authors=new ArrayList<Author>();
        for(Author author:book.getAuthors()) {
            Author foundAuthor = authorService.getAuthorById(author.getId());

            if (foundAuthor == null) {
                return ResponseEntity.notFound().build();
            }
            authors.add(foundAuthor);
        }
        book.setAuthors(authors);

        List<Category> categories=new ArrayList<Category>();
        for(Category category:book.getCategories()) {
            Category foundCategory = categoryService.getCategoryById(category.getId());

            if (foundCategory == null) {
                return ResponseEntity.notFound().build();
            }
            categories.add(foundCategory);
        }
        book.setCategories(categories);

        List<Publisher> publishers=new ArrayList<Publisher>();
        for(Publisher publisher:book.getPublishers()) {
            Publisher foundPublisher = publisherService.getPublisherById(publisher.getId());

            if (foundPublisher == null) {
                return ResponseEntity.notFound().build();
            }
            publishers.add(foundPublisher);
        }
        book.setPublishers(publishers);



        Book createBook=bookService.saveOrUpdateBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book existingBook=bookService.getBookById(id);
        if(existingBook==null) {
            return ResponseEntity.notFound().build();
        }
        List<Author> authors=new ArrayList<Author>();
        for(Author author:book.getAuthors()) {
            Author foundAuthor = authorService.getAuthorById(author.getId());

            if (foundAuthor == null) {
                return ResponseEntity.notFound().build();
            }
            authors.add(foundAuthor);
        }
        book.setAuthors(authors);

        List<Category> categories=new ArrayList<Category>();
        for(Category category:book.getCategories()) {
            Category foundCategory = categoryService.getCategoryById(category.getId());

            if (foundCategory == null) {
                return ResponseEntity.notFound().build();
            }
            categories.add(foundCategory);
        }
        book.setCategories(categories);

        List<Publisher> publishers=new ArrayList<Publisher>();
        for(Publisher publisher:book.getPublishers()) {
            Publisher foundPublisher = publisherService.getPublisherById(publisher.getId());

            if (foundPublisher == null) {
                return ResponseEntity.notFound().build();
            }
            publishers.add(foundPublisher);
        }
        book.setPublishers(publishers);

        book.setId(id);
        bookService.saveOrUpdateBook(book);
        return ResponseEntity.ok(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

