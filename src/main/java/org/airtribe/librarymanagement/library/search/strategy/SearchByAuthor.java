package org.airtribe.librarymanagement.library.search.strategy;

import org.airtribe.librarymanagement.book.Book;
import org.airtribe.librarymanagement.library.Library;

import java.util.ArrayList;

public class SearchByAuthor implements SearchStrategy {
    @Override
    public ArrayList<Book> search(Library library, String searchTerm) {
        return library.getBooksByAuthor().getOrDefault(searchTerm, new ArrayList<>());
    }
}
