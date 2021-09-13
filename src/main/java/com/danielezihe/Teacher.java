package com.danielezihe;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class Teacher implements LibraryMember {
    private String name;
    private String classHandling;
    private String subjectTeaching;
    private Library library;

    public Teacher(String name, String classHandling, String subjectTeaching, Library library) {
        this.name = name;
        this.classHandling = classHandling;
        this.subjectTeaching = subjectTeaching;
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
    public void borrowStatus() {

    }
}
