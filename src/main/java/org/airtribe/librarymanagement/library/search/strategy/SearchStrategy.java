package org.airtribe.librarymanagement.library.search.strategy;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.library.Library;

import java.util.ArrayList;

public interface SearchStrategy {
    ArrayList<Book> search(Library library, String searchTerm);
}
