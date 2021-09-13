package com.danielezihe;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.DefaultArgumentConverter;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 13/09/2021
 */
public class BookRequestConverter extends SimpleArgumentConverter {
    private LibraryManager libraryManager;
    private Library library;
    private Teacher teacher;
    private JuniorStudent juniorStudent;
    private SeniorStudent seniorStudent;
    private Map<String, Book> bookInventory;

    public BookRequestConverter() {
        populateBooksInventory();
        libraryManager = new LibraryManager(bookInventory);
        library = new Library(libraryManager);
        teacher = new Teacher("John", "JSS2", "English", library);
        juniorStudent = new JuniorStudent("Philips", 18, library);
        seniorStudent = new SeniorStudent("Sarah", 20, library);
    }

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        String[] sourceArr = ((String) source).split(":");

        if (sourceArr[0].equals("BookRequest")) {
            if(sourceArr[1].equals("juniorStudent")) {
                return new BookRequest(juniorStudent, sourceArr[2]);
            } else if(sourceArr[1].equals("seniorStudent")) {
                return new BookRequest(seniorStudent, sourceArr[2]);
            } else if(sourceArr[1].equals("teacher")) {
                return new BookRequest(teacher, sourceArr[2]);
            }
        }
        return DefaultArgumentConverter.INSTANCE.convert(source, targetType);
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
