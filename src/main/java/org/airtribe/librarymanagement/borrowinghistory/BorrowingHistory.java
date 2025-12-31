package org.airtribe.librarymanagement.borrowinghistory;

import org.airtribe.librarymanagement.LibraryManagementConstants;
import org.airtribe.librarymanagement.book.Book;

import java.util.Calendar;
import java.util.Date;

public class BorrowingHistory {
    private Book book;
    private Date checkOutDate;
    private Date dueDate;
    private Date returnDate;

    // Constructor
    public BorrowingHistory()
    {

    }

    public BorrowingHistory(Book book, Date checkOutDate) {
        this.book = book;
        this.checkOutDate = checkOutDate;
    }

    public BorrowingHistory(Book book, Date checkOutDate, Date dueDate, Date returnDate) {
        this.book = book;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // Getters
    public Book getBook() {
        return book;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    // Setters
    public void setBook(Book book) {
        this.book = book;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

