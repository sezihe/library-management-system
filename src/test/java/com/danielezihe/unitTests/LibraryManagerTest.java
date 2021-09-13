package com.danielezihe.unitTests;

import com.danielezihe.Book;
import com.danielezihe.LibraryManager;
import com.danielezihe.NullableConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 13/09/2021
 */
public class LibraryManagerTest {
    private Map<String, Book> bookInventory;
    private LibraryManager libraryManager;

    public static final Logger logger = LogManager.getLogger(LibraryManagerTest.class);

    static {
        DOMConfigurator.configure("./src/main/log4j.xml");
    }

    @BeforeEach
    void setUp() {
        populateBooksInventory();
        libraryManager = new LibraryManager(bookInventory);
    }

    @ParameterizedTest
    @CsvSource({"SN182, The Pragmatic Programmer",
                "SN135, Clean Code",
                "SN122, Code Complete",
                "SN155, Refactoring"})
    @DisplayName("Checks if getBook method call returns the right book")
    void shouldReturnACorrectBook(String bookId, String expectedBookTitle) {
        Book book = libraryManager.getBook(bookId);

        Assertions.assertEquals(expectedBookTitle, book.getTitle());
    }

    @ParameterizedTest
    @CsvSource({"SN155, Book Taken",
            "SN988, Book Taken",
            "SN100, Book Taken"})
    @DisplayName("Checks if getBook method call returns 'Book Taken' when book is taken")
    void shouldReturnBookTakenWhenABookIsTaken(String bookId, String expectedOutput) {
        // simulate taking the book
        Book book = libraryManager.getBook(bookId);
        logger.info("(SIMULATED) Giving out book with ID: " + bookId);

        var result = libraryManager.getBook(bookId);

        Assertions.assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @CsvSource({"SN500, null",
                "SN2000, null",
                "SN1000, null"})
    @DisplayName("Checks if getBook method call returns null when book does not exist")
    void shouldReturnNullWhenABookDoesNotExist(String bookId, @ConvertWith(NullableConverter.class) Book expectedOutput) {
        Book book = libraryManager.getBook(bookId);

        Assertions.assertEquals(expectedOutput, book);
    }




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
