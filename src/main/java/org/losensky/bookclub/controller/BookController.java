package org.losensky.bookclub.controller;

import java.util.List;

import org.losensky.bookclub.data.entities.Book;
import org.losensky.bookclub.services.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();

    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
       return bookService.getBookById(id);        
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book.getTitle(), book.getAuthor()).orElseThrow();
        return saved;
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable int id) {
        Book deletedBook = bookService.getBookById(id);
        bookService.deleteBook(id);       
        return deletedBook;
    }
}
