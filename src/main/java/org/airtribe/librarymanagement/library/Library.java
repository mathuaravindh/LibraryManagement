package org.airtribe.librarymanagement.library;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.book.BookStatus;
import org.airtribe.librarymanagement.library.search.factory.SearchStrategyFactory;
import org.airtribe.librarymanagement.library.search.strategy.SearchStrategy;
import org.airtribe.librarymanagement.logger.Logger;
import org.airtribe.librarymanagement.user.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private String branchCode;
    private ArrayList<Book> books;
    private Map<String, ArrayList<Book>> booksByAuthor;
    private Map<String, ArrayList<Book>> booksByISBN;
    private Map<String, ArrayList<Book>> booksByTitle;
    private ArrayList<Patron> patrons;
    private final Logger logger;

    // Constructor

    public Library(Logger logger)
    {
        this.logger = logger;
    }

    public Library(String branchCode, Logger logger) {
        this.branchCode = branchCode;
        this.books = new ArrayList<>();
        this.booksByAuthor = new HashMap<>();
        this.booksByISBN = new HashMap<>();
        this.booksByTitle = new HashMap<>();
        this.patrons = new ArrayList<>();
        this.logger = logger;
    }

    public Library(String branchCode,
                   ArrayList<Book> books,
                   Map<String, ArrayList<Book>> booksByAuthor,
                   Map<String, ArrayList<Book>> booksByISBN,
                   Map<String, ArrayList<Book>> booksByTitle,
                   ArrayList<Patron> patrons,
                   Logger logger) {
        this.branchCode = branchCode;
        this.books = books;
        this.booksByAuthor = booksByAuthor;
        this.booksByISBN = booksByISBN;
        this.booksByTitle = booksByTitle;
        this.patrons = patrons;
        this.logger = logger;
    }

    // Getters
    public String getBranchCode() {
        return branchCode;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Map<String, ArrayList<Book>> getBooksByAuthor() {
        return booksByAuthor;
    }

    public Map<String, ArrayList<Book>> getBooksByISBN() {
        return booksByISBN;
    }

    public Map<String, ArrayList<Book>> getBooksByTitle() {
        return booksByTitle;
    }

    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    // Setters
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setBooksByAuthor(Map<String, ArrayList<Book>> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }

    public void setBookByISBN(Map<String, ArrayList<Book>> bookByISBN) {
        this.booksByISBN = bookByISBN;
    }

    public void setPatrons(ArrayList<Patron> patrons) {
        this.patrons = patrons;
    }

    public void setBookByTitle(Map<String, ArrayList<Book>> booksByISBN) {
        this.booksByTitle = booksByISBN;
    }


    public void addBook(Book book) {
        books.add(book);
        booksByAuthor.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);
        booksByISBN.computeIfAbsent(book.getIsbn(), k -> new ArrayList<>()).add(book);
        booksByTitle.computeIfAbsent(book.getTitle(), k -> new ArrayList<>()).add(book);
    }

    public void updateBookStatus(Book book, BookStatus bookStatus) {
        book.setStatus(bookStatus);
    }

    public ArrayList<Book> search(String type, String searchTerm) {
        SearchStrategy strategy = SearchStrategyFactory.getSearchStrategy(type);
        return strategy.search(this, searchTerm);
    }

    public void addPatron(Patron patron) {
        if (this.patrons.stream().anyMatch(p -> p.getEmail().equals(patron.getEmail())))
            logger.LogError("Patron " + patron.getEmail() + " already exists!");
        else {
            this.patrons.add(patron);
            logger.LogInfo("Patron " + patron.getEmail() + " added succesfully!");
        }
    }
}
