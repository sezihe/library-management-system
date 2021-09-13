package com.danielezihe.unitTests;

import com.danielezihe.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class LibraryTest {
    private Library library;
    private Map<String, Book> bookInventory;
    LibraryManager libraryManager;

    public static final Logger logger = LogManager.getLogger(LibraryTest.class);

    static {
        // Log4j
        DOMConfigurator.configure("./src/main/log4j.xml");
    }

    @BeforeEach
    void setUp() {
        populateBooksInventory();
        libraryManager = new LibraryManager(bookInventory);
        library = new Library(libraryManager);
    }

    @ParameterizedTest
    @ValueSource(strings = {"SN792", "SN122", "SN282", "SN110"})
    @DisplayName("Checks if Library gives out the correct requested book")
    void checksIfLibraryGivesOutTheCorrectRequestedBook() {
        String requestedBookId = "SN135";
        String requestedBookTitle = "Clean Code";

        BookRequest bookRequest = new BookRequest(new JuniorStudent("Daniel", 18, library), requestedBookId);
        Book book = library.giveOutBook(bookRequest);

        Assertions.assertEquals(requestedBookTitle, book.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = {"SN155", "SN988", "SN100", "SN126"})
    @DisplayName("Checks if Library says 'Book Taken' when requested book has been taken")
    void checksIfLibrarySaysBookTakenWhenRequestedBookHasBeenTaken(String bookId) {
        BookRequest bookRequest = new BookRequest(new JuniorStudent("Daniel", 18, library), bookId);

        // simulate a request coming in for a book
        Book book = library.giveOutBook(bookRequest);
        logger.info("(SIMULATED) Library is giving out book with Title: " + book.getTitle());

        Assertions.assertEquals("Book Taken", library.giveOutBook(bookRequest));
    }




    // UTILITIES

    void populateBooksInventory() {
        bookInventory = new HashMap<>();
        bookInventory.put("SN182", new Book("SN182", "The Pragmatic Programmer", new String[]{"David Thomas", "Andrew Hunt"}, 5));
        bookInventory.put("SN135", new Book("SN135", "Clean Code", new String[]{"Robert C. Martin"}, 3));
        bookInventory.put("SN122", new Book("SN122", "Code Complete", new String[]{"Steve McConnell"}, 4));
        bookInventory.put("SN155", new Book("SN155", "Refactoring", new String[]{"Martin Fowler"}, 1));
        bookInventory.put("SN298", new Book("SN298", "Head First Design Patterns", new String[]{"Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson"}, 2));
        bookInventory.put("SN792", new Book("SN792", "The Mythical Man-Month", new String[]{"Frederick P. Brooks Jr"}, 5));
        bookInventory.put("SN988", new Book("SN988", "The Clean Coder", new String[]{"Robert Martin"}, 1));
        bookInventory.put("SN282", new Book("SN282", "Working Effectively with Legacy Code", new String[]{"Michael Feathers"}, 2));
        bookInventory.put("SN126", new Book("SN126", "Design Patterns", new String[]{"Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"}, 1));
        bookInventory.put("SN100", new Book("SN100", "Cracking the Coding Interview", new String[]{"Gayle Laakmann McDowell"}, 1));
        bookInventory.put("SN110", new Book("SN110", "Rework", new String[]{"Jason Fried", "David Heinemeier Hansson"}, 2));
    }
}
