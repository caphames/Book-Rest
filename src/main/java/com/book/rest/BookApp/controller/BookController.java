package com.book.rest.BookApp.controller;


import com.book.rest.BookApp.model.BookModel;
import com.book.rest.BookApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookModel> getAllBooks()
    {
        return bookRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> findById(@PathVariable Long id){
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public BookModel createBook(@RequestBody BookModel bookModel){
        return bookRepository.save(bookModel);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel bookdetails){
        Optional<BookModel> book = bookRepository.findById(id);

        if (book.isPresent()){
            BookModel updateBook = book.get();
            updateBook.setAuthor(bookdetails.getAuthor());
            updateBook.setTitle(bookdetails.getTitle());
            return ResponseEntity.ok(bookRepository.save(updateBook));
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        Optional<BookModel> book = bookRepository.findById(id);

        if (book.isPresent()){
            bookRepository.delete(book.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}

