package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfFileUploadException;
import org.example.app.exceptions.BookShelfRegexException;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookIdToRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookShelfExceptionHandler {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    Logger logger = Logger.getLogger(BookShelfExceptionHandler.class);

    @ExceptionHandler(BookShelfFileUploadException.class)
    public String handleFileUploadException(BookShelfFileUploadException exception) {
        logger.error(exception.getMessage());
        return "redirect:/books/shelf";
    }

    @ExceptionHandler(BookShelfRegexException.class)
    public String handleRegexException(Model model, BookShelfRegexException exception) {
        logger.error(exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }
}
