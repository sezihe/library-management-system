package com.danielezihe;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public abstract class Student implements LibraryMember {
    // public abstract void requestBook();
    private String name;
    private int age;
    private int borrowedBookCount;

    public static final Logger logger = LogManager.getLogger(Student.class);

    public Student(String name, int age, int borrowedBookCount) {
        DOMConfigurator.configure("./src/main/log4j.xml");
        this.name = name;
        this.age = age;
        this.borrowedBookCount = borrowedBookCount;
    }

    public void increaseBorrowedBookCount() {
        borrowedBookCount++;
    }

    abstract void checkBorrowStatus();

    @Override
    public void borrowStatus() {
        if(borrowedBookCount > 1)
            logger.info("You currently have " + borrowedBookCount + " borrowed books");
        logger.info("You currently have " + borrowedBookCount + " borrowed book");
    }
}
