package com.danielezihe;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public class Library {
    private LibraryManager libraryManager;
    private PriorityQueue<BookRequest> requests;

    public static final Logger logger = LogManager.getLogger(Library.class);

    public Library(LibraryManager libraryManager) {
        DOMConfigurator.configure("./src/main/log4j.xml");
        this.libraryManager = libraryManager;
    }

    public <T> T giveOutBook(BookRequest bookRequest) {
        var result = libraryManager.getBook(Objects.requireNonNull(bookRequest).requestingBookId());
        if(result == null) {
            logger.error("Book does not exist");
            return null;
        } else if(result.equals("Book Taken")) {
            logger.error("Book Taken");
            return (T) "Book Taken";
        } else if(result instanceof Book){
            logger.info("(BOOK BORROWED) Giving out book named '" + ((Book) result).getTitle() + "' to " + bookRequest.libraryMember().getClass().getSimpleName());
            return (T) result;
        } else {
            logger.error("Fatal error: " + result);
            return null;
        }
    }

    public void giveOutBook(BookRequest[] requests) {
        this.requests = new PriorityQueue<>(requests.length, new RequestComparator());
        this.requests.addAll(Arrays.asList(requests));

        distributeBooks();
    }

    public void distributeBooks() {
        while (!requests.isEmpty()) {
            BookRequest request = requests.poll();
            var result = libraryManager.getBook(Objects.requireNonNull(request).requestingBookId());
            if(result == null) {
                // error handling
                logger.error("Book does not exist!");
            } else if(result.equals("Book Taken")) {
                logger.error("Book Taken");
            } else {
                logger.info("(BOOK BORROWED) Giving out book named '" + ((Book) result).getTitle() + "' to " + request.libraryMember().getClass().getSimpleName());
            }
        }
    }

    public void addToWaitingQueue(BookRequest[] requests) {
        this.requests = new PriorityQueue<>(requests.length, new RequestComparator());
        this.requests.addAll(Arrays.asList(requests));
    }

    private class RequestComparator implements Comparator<BookRequest> {
        @Override
        public int compare(BookRequest o1, BookRequest o2) {
            if(o1.libraryMember() instanceof Teacher && o2.libraryMember() instanceof Student) {
                return -1;
            } else if(o1.libraryMember() instanceof Student) {
                if(o1.libraryMember() instanceof SeniorStudent && o2.libraryMember() instanceof JuniorStudent) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;
        }
    }

    public PriorityQueue<BookRequest> getQueue() {
        return requests;
    }
}
