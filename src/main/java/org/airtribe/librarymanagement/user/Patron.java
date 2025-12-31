package org.airtribe.librarymanagement.user;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.borrowinghistory.BorrowingHistory;
import org.airtribe.librarymanagement.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Patron extends User {

    private ArrayList<BorrowingHistory> borrowingHistoryList;

    private final Logger logger;


    // Constructor
    public Patron(int patronId, String name, String email, Logger logger) {
        super(patronId, name, email);
        this.borrowingHistoryList = new ArrayList<>();
        this.logger = logger;
    }


    public ArrayList<BorrowingHistory> getBorrowingHistoryList() {
        return borrowingHistoryList;
    }


    public void setBorrowingHistoryList(ArrayList<BorrowingHistory> borrowingHistoryList) {
        this.borrowingHistoryList = borrowingHistoryList;
    }

    public void addBorrowingHistory(BorrowingHistory history) {
        borrowingHistoryList.add(history);
    }

    public Optional<BorrowingHistory> getUnreturnedBorrowing(int bookId) {
        return borrowingHistoryList.stream().filter(borrowingHistory -> borrowingHistory.getBook().getBookId() == bookId && borrowingHistory.getReturnDate() == null).findFirst();
    }

    public void borrowBook(Book book, BorrowingHistory borrowingHistory) {
        this.addBorrowingHistory(borrowingHistory);
    }

    public void printCurrentBorrowing() {

        //current borrowing is computed by filtering history records with returndate as null
        List<BorrowingHistory> currentBorrowing = borrowingHistoryList.stream().filter(b -> b.getReturnDate() == null).collect(Collectors.toList());

        if (currentBorrowing.isEmpty()) {
            logger.LogInfo(this.getUserName() + " has no currently borrowed books.");
        } else {
            System.out.println(this.getUserName() + " is currently borrowing the following books:");
            for (BorrowingHistory borrowingHistory : currentBorrowing) {
                System.out.println("- " + borrowingHistory.getBook().getTitle() + " by " + borrowingHistory.getBook().getAuthor()
                        + " on " + borrowingHistory.getCheckOutDate() + " due on " + borrowingHistory.getDueDate());
            }
        }
    }

    public void printBorrowingHistory() {
        if (this.borrowingHistoryList.isEmpty()) {
            logger.LogInfo(this.getUserName() + " has no borrowing history.");
        } else {
            System.out.println(this.getUserName() + "'s borrowing history:");
            for (BorrowingHistory borrowingHistory : borrowingHistoryList) {
                System.out.println("- " + borrowingHistory.getBook().getTitle() + " by " + borrowingHistory.getBook().getAuthor()
                        + " on " + borrowingHistory.getCheckOutDate() + " due on " + borrowingHistory.getDueDate()
                        + " returned on " + ((borrowingHistory.getReturnDate() != null) ? borrowingHistory.getReturnDate() : "(not returned yet)."));
            }
        }
    }

}


