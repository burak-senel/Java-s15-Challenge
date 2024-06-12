package library;

import java.util.Date;

public abstract class Book {
    protected String bookID;
    protected String author;
    protected String name;
    protected String status;
    protected String edition;
    protected Date dateOfPurchase;

    public Book(String bookID, String author, String name, String edition, Date dateOfPurchase) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.status = "available";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public void getTitle() {
        System.out.println("Title: " + name);
    }

    public void getAuthor() {
        System.out.println("Author: " + author);
    }

    public void changeOwner(Reader reader) {
        System.out.println("Owner changed to: " + reader.getName());
    }

    public void getOwner() {
        System.out.println("Current owner information could be retrieved from the system.");
    }

    public abstract void display();

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
