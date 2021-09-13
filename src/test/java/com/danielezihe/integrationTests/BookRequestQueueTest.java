package com.danielezihe.integrationTests;

import com.danielezihe.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class BookRequestQueueTest {
    private LibraryManager libraryManager;
    private Library library;
    private Map<String, Book> bookInventory;

    @BeforeEach
    @Disabled("Not implemented yet")
    void setUp() {
        populateBooksInventory();

        libraryManager = new LibraryManager(bookInventory);
        library = new Library(libraryManager);
    }

    @ParameterizedTest
    @CsvSource({"BookRequest:juniorStudent:SN100, BookRequest:teacher:SN100",
                "BookRequest:seniorStudent:SN988, BookRequest:teacher:SN988",
                "BookRequest:juniorStudent:SN122, BookRequest:teacher:SN122",
                "BookRequest:seniorStudent:SN298, BookRequest:teacher:SN298"})
    @DisplayName("Check that priority is given to a Teacher over a Student when a request comes in")
    void shouldConfirmThatTheTeacherIsOnTopOfThePriorityQueue(@ConvertWith(BookRequestConverter.class) BookRequest studentRequest, @ConvertWith(BookRequestConverter.class) BookRequest teacherRequest) {
        BookRequest[] requests = new BookRequest[]{studentRequest, teacherRequest};

        library.addToWaitingQueue(requests);

        PriorityQueue<BookRequest> waitingQueue = library.getQueue();

        Assertions.assertEquals(teacherRequest, waitingQueue.peek());
        Assertions.assertSame(teacherRequest, waitingQueue.peek());
    }

    @ParameterizedTest
    @CsvSource({"BookRequest:juniorStudent:SN100, BookRequest:seniorStudent:SN100",
            "BookRequest:juniorStudent:SN110, BookRequest:seniorStudent:SN110",
            "BookRequest:juniorStudent:SN155, BookRequest:seniorStudent:SN155",
            "BookRequest:juniorStudent:SN282, BookRequest:seniorStudent:SN282"})
    @DisplayName("Check that priority is given to a Senior Student over a Junior when a request comes in")
    void shouldConfirmThatTheSeniorStudentIsAboveTheJuniorOnThePriorityQueue(@ConvertWith(BookRequestConverter.class) BookRequest juniorStudentRequest, @ConvertWith(BookRequestConverter.class) BookRequest seniorStudentRequest) {
        BookRequest[] requests = new BookRequest[]{juniorStudentRequest, seniorStudentRequest};

        library.addToWaitingQueue(requests);

        PriorityQueue<BookRequest> waitingQueue = library.getQueue();

        Assertions.assertEquals(seniorStudentRequest, waitingQueue.peek());
        Assertions.assertSame(seniorStudentRequest, waitingQueue.peek());
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
