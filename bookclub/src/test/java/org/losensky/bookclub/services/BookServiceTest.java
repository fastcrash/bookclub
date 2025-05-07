package org.losensky.bookclub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.losensky.bookclub.data.entities.Book;
import org.losensky.bookclub.data.repositories.BookRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository mockBookRepository;    
    
    @InjectMocks
    BookService bookService;    

    // @BeforeEach
    // void setUp() {
    //     bookService = new BookService(mockBookRepository);
      
    // }
   

    @Test
    void testAddBook() {        

         // Arrange
        String title = "1984";
        String author = "George Orwell";

        Book mockBook = new Book();
        mockBook.setTitle(title);
        mockBook.setAuthor(author);

        when(mockBookRepository.save(any(Book.class))).thenReturn(mockBook);

        // Act
        Book result = bookService.addBook(title, author).orElseThrow();

        // Assert
        assertEquals(mockBook, result);        
        verify(mockBookRepository).save(result);
    }

    @Test
    void testGetBookById() {
        int bookId = 42;
        Book book = new Book();
        when(mockBookRepository.findById(bookId)).thenReturn(book);
        Book result = bookService.getBookById(bookId);
        assertSame(book, result);
    }
}
