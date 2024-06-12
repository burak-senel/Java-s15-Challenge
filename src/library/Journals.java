package library;

import java.util.Date;

public class Journals extends Book {
    public Journals(String bookID, String author, String name, String edition, Date dateOfPurchase) {
        super(bookID, author, name, edition, dateOfPurchase);
    }

    @Override
    public void display() {
        System.out.println("Journal - ID: " + bookID + ", Name: " + name + ", Author: " + author + ", Status: " + status);
    }
}
