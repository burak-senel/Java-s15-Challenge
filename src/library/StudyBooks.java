package library;

import java.util.Date;

public class StudyBooks extends Book{
    public StudyBooks(String bookID, String author, String name, String edition, Date dateOfPurchase) {
        super(bookID, author, name, edition, dateOfPurchase);
    }

    @Override
    public void display() {
        System.out.println("Study Book - ID: " + bookID + ", Name: " + name + ", Author: " + author + ", Status: " + status);
    }
}
