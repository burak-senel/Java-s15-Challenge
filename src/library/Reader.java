package library;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books;

    public Reader(String name) {
        super(name, null); // Password is not needed for Reader
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void borrowBook(Book book) {
        if (books.size() < 5) {
            books.add(book);
        } else {
            System.out.println("You have reached the limit of borrowed books.");
        }
    }

    public void returnBook(Book book) {
        books.remove(book);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Reader");
    }
}
