package com.danielezihe;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public interface LibraryMember {
    <T> T requestBook(String bookId);
    void borrowStatus();
}
