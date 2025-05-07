package org.losensky.bookclub.data.repositories;



import org.losensky.bookclub.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find books by title or author, you can add methods like:
    // List<Book> findByTitle(String title);
    // List<Book> findByAuthor(String author);
    Book findById(int id);    

}
