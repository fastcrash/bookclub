package org.losensky.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.losensky.bookclub.data.entities.Book;
import org.losensky.bookclub.data.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> addBook(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        bookRepository.save(book);
        return Optional.of(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(int id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            bookRepository.delete(book);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }


}
