package library;

import java.util.Date;

public class Magazines extends Book {
    public Magazines(String bookID, String author, String name, String edition, Date dateOfPurchase) {
        super(bookID, author, name, edition, dateOfPurchase);
    }

    @Override
    public void display() {
        System.out.println("Magazine - ID: " + bookID + ", Name: " + name + ", Author: " + author + ", Status: " + status);
    }
}

