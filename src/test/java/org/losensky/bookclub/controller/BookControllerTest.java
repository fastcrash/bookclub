package org.losensky.bookclub.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.losensky.bookclub.data.entities.Book;
import org.losensky.bookclub.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setAuthor("Author A");

        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setAuthor("Author B");
        
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(2)))
        .andExpect(jsonPath("$[0].title", is("Book One")))
        .andExpect(jsonPath("$[1].author", is("Author B")));

    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");

        when(bookService.addBook(anyString(), anyString())).thenReturn(Optional.of(book));

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Book")))
                .andExpect(jsonPath("$.author", is("New Author")));
    }
}
