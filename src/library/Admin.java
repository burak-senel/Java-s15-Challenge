package library;

import java.util.Date;

public class Admin extends Person {

    public Admin(String name, String password) {
        super(name, password);
    }





    public void addBook(Book book, Library library) {
        library.addBook(book);
    }



    public void displayRole() {
        System.out.println("Role: Admin");
    }
}
