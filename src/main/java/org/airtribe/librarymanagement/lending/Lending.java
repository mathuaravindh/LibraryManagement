package org.airtribe.librarymanagement.lending;

import org.airtribe.librarymanagement.LibraryManagementConstants;
import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.book.BookStatus;
import org.airtribe.librarymanagement.borrowinghistory.BorrowingHistory;
import org.airtribe.librarymanagement.library.Library;
import org.airtribe.librarymanagement.logger.Logger;
import org.airtribe.librarymanagement.user.Patron;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class Lending {
    private Library library;
    private final Logger logger;

    public Lending(Library library, Logger logger)
    {
        this.library = library;
        this.logger = logger;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void checkOutBook(Patron patron, Book book, BorrowingHistory borrowingHistory)
    {
        if(!library.search("isbn", book.getIsbn()).stream().findAny().isPresent())
            logger.LogError("Book " + book.getIsbn() + " does not exist.");
        else if(book.getStatus() != BookStatus.AVAILABLE)
            logger.LogError("Book " + book.getIsbn() + " is not available to checkout.");
        else {
            borrowingHistory.setBook(book);
            borrowingHistory.setCheckOutDate(new Date());
            Date dueDate = this.computeDueDate(borrowingHistory.getCheckOutDate());
            borrowingHistory.setDueDate(dueDate);
            patron.addBorrowingHistory(borrowingHistory);
            library.updateBookStatus(book, BookStatus.CHECKED_OUT);
            logger.LogInfo("Book " + book.getIsbn() + "  is successfully checked out by " + patron.getUserName());
        }
    }

    public void returnBook(Patron patron, Book book)
    {
        Optional<BorrowingHistory> borrowing = patron.getUnreturnedBorrowing(book.getBookId());
        if(borrowing.isPresent()) {
            BorrowingHistory borrowingHistory = borrowing.get();
            borrowingHistory.setReturnDate(new Date());
            library.updateBookStatus(book, BookStatus.AVAILABLE);
            logger.LogInfo("Book " + book.getIsbn() + "  is successfully returned by " + patron.getUserName());
        }
        else {
            logger.LogError("No such current borrowing exist for " + patron.getUserName());
        }
    }

    public Date computeDueDate(Date checkOutDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.DAY_OF_YEAR, LibraryManagementConstants.DUE_TIME_DAYS);
        return calendar.getTime();
    }
}
