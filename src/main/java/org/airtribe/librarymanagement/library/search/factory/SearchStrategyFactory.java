package org.airtribe.librarymanagement.library.search.factory;

import org.airtribe.librarymanagement.library.search.strategy.SearchByAuthor;
import org.airtribe.librarymanagement.library.search.strategy.SearchByIsbn;
import org.airtribe.librarymanagement.library.search.strategy.SearchByTitle;
import org.airtribe.librarymanagement.library.search.strategy.SearchStrategy;

public class SearchStrategyFactory {
    public static SearchStrategy getSearchStrategy(String type) {
        switch (type.toLowerCase()) {
            case "title":
                return new SearchByTitle();
            case "author":
                return new SearchByAuthor();
            case "isbn":
                return new SearchByIsbn();
            default:
                throw new IllegalArgumentException("Invalid search type: " + type);
        }
    }
}