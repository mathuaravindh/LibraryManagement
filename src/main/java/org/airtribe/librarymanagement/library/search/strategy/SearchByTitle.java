package org.airtribe.librarymanagement.library.search.strategy;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.library.Library;

import java.util.ArrayList;

public class SearchByTitle implements SearchStrategy {
    @Override
    public ArrayList<Book> search(Library library, String searchTerm) {
        return library.getBooksByTitle().getOrDefault(searchTerm, new ArrayList<>());
    }
}
