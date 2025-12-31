package org.airtribe.librarymanagement.library.search.strategy;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.library.Library;

import java.util.ArrayList;

public class SearchByIsbn implements SearchStrategy {
    @Override
    public ArrayList<Book> search(Library library, String searchTerm) {
        return library.getBooksByISBN().getOrDefault(searchTerm, new ArrayList<>());
    }
}
