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
        var result = library.giveOutBook(new BookRequest(this, bookId));
        if(result == null) {
            return null;
        } else if(result.equals("Book Taken")) {
            return (T) "Book Taken";
        } else if(result instanceof Book){
            return (T) result;
        } else {
            return null;
        }
    }

    @Override
    void checkBorrowStatus() {

    }
}
