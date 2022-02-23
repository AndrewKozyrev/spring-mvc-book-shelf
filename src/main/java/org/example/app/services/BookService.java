package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository<Book> bookRepo;
    private final Logger logger = Logger.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }


    public void saveBook(Book book) {
        if (book.getAuthor().isEmpty() && book.getTitle().isEmpty() && book.getSize() == null) {
            logger.info("error persisting book: " + book);
        } else {
            bookRepo.store(book);
        }
    }

    public boolean removeBookById(String bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean removeByRegex(String regex) {
        return bookRepo.removeItemByRegex(regex);
    }

    private void defaultInit() {
        logger.info("default INIT in book service");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book service");
    }
}
