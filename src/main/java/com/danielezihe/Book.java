package com.danielezihe;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 12/09/2021
 */
public final class Book {
    private String Id;
    private final String title;
    private String[] authors;
    private int quantityInStock;

    public Book(String id, String title, String[] authors, int quantityInStock) {
        Id = id;
        this.title = title;
        this.authors = authors;
        this.quantityInStock = quantityInStock;
    }

    public void decreaseBookQuantityCount() {
        quantityInStock--;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }
}
