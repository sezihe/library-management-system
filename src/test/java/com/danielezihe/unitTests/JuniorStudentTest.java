package com.danielezihe.unitTests;

import com.danielezihe.Books;
import com.danielezihe.JuniorStudent;
import com.danielezihe.Library;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
class JuniorStudentTest {
    private JuniorStudent juniorStudent;
    private Map<Books, Integer> bookInventory;
    private Library library;

    public static final Logger logger = LogManager.getLogger(JuniorStudentTest.class);
    
    @BeforeAll
    @Disabled("Not implemented yet")
    void setUp() {
        // Log4j
        DOMConfigurator.configure("./src/main/log4j.xml");

        populateBooksInventory();

        juniorStudent = new JuniorStudent("Daniel", 20);
        library = new Library(bookInventory);
    }

    @Test
    @DisplayName("Checks if a Book request returns the actual book requested")
    @Disabled("Not implemented yet")
    void checksIfABookRequestReturnsTheActualBookRequested() {
        String bookId = "SN298";

        Books requestedBook = juniorStudent.requestBook(bookId);
        Books correctBookWithThatId = juniorStudent.requestBook(bookId);

        logger.info("Requested Book: " + requestedBook);
        logger.info("Correct Book To be given: " + correctBookWithThatId);

        Assertions.assertEquals(correctBookWithThatId, requestedBook);
        Assertions.assertSame(correctBookWithThatId, requestedBook);
    }

    @Test
    @DisplayName("Checks if a Book request returns 'Book Taken' if book is taken")
    @Disabled("Not implemented yet")
    void checksIfABookRequestReturnsBookTakenIfBookIsTaken() {
        String bookId = "SN100";

        // simulate a user taking a book
        Books books = juniorStudent.requestBook(bookId);
        logger.info("(Simulated) Library giving out book with Title: " + books.getTitle());

        Assertions.assertEquals("Book Taken", juniorStudent.requestBook(bookId));
    }

    void populateBooksInventory() {
        bookInventory = new HashMap<>();
        bookInventory.put("SN182", new Books("SN182", "The Pragmatic Programmer", new String[]{"David Thomas", "Andrew Hunt"}, 5));
        bookInventory.put("SN135", new Books("SN135", "Clean Code", new String[]{"Robert C. Martin"}, 3));
        bookInventory.put("SN122", new Books("SN122", "Code Complete", new String[]{"Steve McConnell"}, 4));
        bookInventory.put("SN155", new Books("SN155", "Refactoring", new String[]{"Martin Fowler"}, 1));
        bookInventory.put("SN298", new Books("SN298", "Head First Design Patterns", new String[]{"Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson"}, 2));
        bookInventory.put("SN792", new Books("SN792", "The Mythical Man-Month", new String[]{"Frederick P. Brooks Jr"}, 5));
        bookInventory.put("SN988", new Books("SN988", "The Clean Coder", new String[]{"Robert Martin"}, 1));
        bookInventory.put("SN282", new Books("SN282", "Working Effectively with Legacy Code", new String[]{"Michael Feathers"}, 2));
        bookInventory.put("SN126", new Books("SN126", "Design Patterns", new String[]{"Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"}, 1));
        bookInventory.put("SN100", new Books("SN100", "Cracking the Coding Interview", new String[]{"Gayle Laakmann McDowell"}, 1));
        bookInventory.put("SN110", new Books("SN110", "Rework", new String[]{"Jason Fried", "David Heinemeier Hansson"}, 2));
    }

}