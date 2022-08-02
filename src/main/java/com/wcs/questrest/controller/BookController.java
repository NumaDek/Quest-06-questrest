package com.wcs.questrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.questrest.entity.Book;
import com.wcs.questrest.repository.BookRepository;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        Book bookToCreate = new Book(book.getTitle(), book.getAuthor(), book.getDescription());
        bookRepository.save(bookToCreate);
        return (bookToCreate);
    }

    @GetMapping("{id}")
    public Book getBook(Model model, @PathVariable Long id) {
        Optional<Book> bookOptional =  bookRepository.findById(id);
        return bookOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Book> getAllBook(Model model) {
        return this.bookRepository.findAll();
    }

    @GetMapping("search/{word}")
    public List<Book> getAllBookByDescription(@PathVariable String word) {
        return bookRepository.findByTitleContainingOrDescriptionContaining(word, word);
    }

    @PutMapping("{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Book bookToUpdate = bookOptional.get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        bookRepository.save(bookToUpdate);
        return (bookToUpdate);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.bookRepository.deleteById(id);
    }
}