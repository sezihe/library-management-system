package com.danielezihe;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class SeniorStudent extends Student {
    Library library;

    public SeniorStudent(String name, int age, Library library) {
        super(name, age, 0);
        this.library = library;
    }

    @Override
    public <T> T requestBook(String bookId) {
        return null;
    }

    @Override
    void checkBorrowStatus() {

    }
}
