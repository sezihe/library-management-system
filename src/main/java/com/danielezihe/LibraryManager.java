package com.danielezihe;

import java.util.Map;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 13/09/2021
 */
public class LibraryManager {
    private Map<String, Book> bookInventory;

    public LibraryManager(Map<String, Book> bookInventory) {
        this.bookInventory = bookInventory;
    }

    public <T> T getBook(String bookId) {
        if(!bookInventory.containsKey(bookId))
            return null;
        Book book = bookInventory.get(bookId);

        if(book.getQuantityInStock() < 1)
            return (T) "Book Taken";

        book.decreaseBookQuantityCount();
        return (T) book;
    }
}
