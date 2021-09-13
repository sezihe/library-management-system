package com.danielezihe.integrationTests;

import com.danielezihe.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class BookRequestQueueTest {
    private Library library;
    private Teacher teacher;
    private JuniorStudent juniorStudent;
    private SeniorStudent seniorStudent;
    private Map<String, Book> bookInventory;

    @BeforeEach
    @Disabled("Not implemented yet")
    void setUp() {
        populateBooksInventory();

        library = new Library(bookInventory);
        teacher = new Teacher("John", "JSS2", "English");
        juniorStudent = new JuniorStudent("Philips", 18);
        seniorStudent = new SeniorStudent("Sarah", 20);
    }

    @ParameterizedTest
    @CsvSource({"new BookRequest(juniorStudent, \"SN100\");, teacherRequest = new BookRequest(teacher, \"SN100\");",
                "new BookRequest(seniorStudent, \"SN100\");, teacherRequest = new BookRequest(teacher, \"SN100\");"})
    @Disabled("Not implemented yet")
    @DisplayName("Check that priority is given to a Teacher over a Student when a request comes in")
    void checkThatPriorityIsGivenToATeacherOtherThanAStudentWhenARequestComesIn(BookRequest studentRequest,
                                                                                BookRequest teacherRequest) {
//        BookRequest juniorStudentRequest = new BookRequest(juniorStudent, "SN100");
//        BookRequest seniorStudentRequest = new BookRequest(seniorStudent, "SN100");
//        BookRequest teacherRequest = new BookRequest(teacher, "SN100");

        BookRequest[] requests = new BookRequest[]{studentRequest, teacherRequest};

        library.giveOutBook(requests);

        PriorityQueue<BookRequest> waitingQueue = library.getQueue();

        Assertions.assertEquals(teacherRequest, waitingQueue.peek());
        Assertions.assertSame(teacherRequest, waitingQueue.peek());
    }

    @Test
    @Disabled("Not implemented yet")
    @DisplayName("Check that priority is given to a Senior Student over a Junior when a request comes in")
    void checkThatPriorityIsGivenToASeniorStudentOtherThanAJuniorWhenARequestComesIn() {
        BookRequest juniorStudentRequest = new BookRequest(juniorStudent, "SN100");
        BookRequest seniorStudentRequest = new BookRequest(seniorStudent, "SN100");

        BookRequest[] requests = new BookRequest[]{juniorStudentRequest, seniorStudentRequest};

        library.giveOutBook(requests);

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
