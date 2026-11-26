package com.example.correction_spring_aspect.service;

import com.example.correction_spring_aspect.entity.Book;
import com.example.correction_spring_aspect.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceBefore {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceBefore.class);
    private final BookRepository bookRepository;

    public BookServiceBefore(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(String name, String author) {
        long start = System.nanoTime();
        try {
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            return bookRepository.save(book);
        } finally {
            LOGGER.info("save : {} ms", (System.nanoTime() - start) / 1_000_000.0);
        }
    }

    public Book findById(long id) {
        LOGGER.info("Début : findById");
        long start = System.nanoTime();
        try {
            return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        } finally {
            LOGGER.info("findById : {} ms", (System.nanoTime() - start) / 1_000_000.0);
            LOGGER.info("Fin : findById");
        }
    }

    public List<Book> findAll() {
        LOGGER.info("Début : findAll");
        try {
            return (List<Book>) bookRepository.findAll();
        } finally {
            LOGGER.info("Fin : findAll");
        }
    }
}
