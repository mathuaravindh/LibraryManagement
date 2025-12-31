package org.airtribe.librarymanagement;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.book.BookStatus;
import org.airtribe.librarymanagement.borrowinghistory.BorrowingHistory;
import org.airtribe.librarymanagement.lending.Lending;
import org.airtribe.librarymanagement.library.Library;
import org.airtribe.librarymanagement.logger.ConsoleLogger;
import org.airtribe.librarymanagement.user.Patron;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Library library = new Library("Branch001", new ConsoleLogger());


        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", BookStatus.AVAILABLE);
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee", "9780061120084", BookStatus.AVAILABLE);
        Book book3 = new Book(3, "1984", "George Orwell", "9780451524935", BookStatus.AVAILABLE);


        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);


        Patron patron1 = new Patron(1, "Alice Smith", "alice@gmail.com", new ConsoleLogger());
        Patron patron2 = new Patron(2, "Bob Johnson", "bob@gmail.com", new ConsoleLogger());
        Patron patron3 = new Patron(3, "Bob J", "bob@gmail.com", new ConsoleLogger());

        library.addPatron(patron1);
        library.addPatron(patron2);


        System.out.println("Books in the library:");
        for (Book book : library.getBooks()) {
            book.display();
            System.out.println();
        }

        //search
        ArrayList<Book> byAuthor = library.search("author", "Harper Lee");
        System.out.println("Books searched by author:");
        for (Book book : byAuthor) {
            book.display();
            System.out.println();
        }

        ArrayList<Book> byISBN = library.search("isbn", "9780451524935");
        System.out.println("Books searched by ISBN:");
        for (Book book : byISBN) {
            book.display();
            System.out.println();
        }


        //chekout
        Lending lending = new Lending(library, new ConsoleLogger());
        lending.checkOutBook(patron1, book1, new BorrowingHistory());

        System.out.println("Updated book status:");
        book1.display();
        System.out.println();

        //test error logging for book already checked out
        lending.checkOutBook(patron1, book1, new BorrowingHistory());

        //return
        lending.returnBook(patron1, book1);

        lending.checkOutBook(patron1, book2, new BorrowingHistory());

        //borrowingHistory
        patron1.printCurrentBorrowing();
        patron1.printBorrowingHistory();

        System.out.println("Updated book status:");
        book1.display();

        //test error logging for return
        lending.returnBook(patron1, book1);
    }
}
