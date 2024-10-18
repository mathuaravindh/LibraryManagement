package org.airtribe.librarymanagement.book;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private BookStatus status;

    public Book(int bookId, String title, String author, String isbn, BookStatus status)
    {
        this.bookId = bookId;
        this.title = title;
        this.author =  author;
        this.isbn = isbn;
        this.status = status;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void display() {
        System.out.println("Book ID: " + this.getBookId());
        System.out.println("Title: " + this.getTitle());
        System.out.println("Author: " + this.getAuthor());
        System.out.println("ISBN: " + this.getIsbn());
        System.out.println("Status: " + this.getStatus());
    }
}
