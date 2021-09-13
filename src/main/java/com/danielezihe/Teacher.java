package com.danielezihe;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class Teacher implements LibraryMember {
    private String name;
    private String classHandling;
    private String subjectTeaching;

    public Teacher(String name, String classHandling, String subjectTeaching) {
        this.name = name;
        this.classHandling = classHandling;
        this.subjectTeaching = subjectTeaching;
    }

    @Override
    public <T> T requestBook(String bookId) {
        return null;
    }

    @Override
    public void borrowStatus() {

    }
}
