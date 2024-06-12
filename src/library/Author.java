package library;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name) {
        super(name, null); // Password is not needed for Author
        books = new ArrayList<>();
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void showBook() {
        for (Book book : books) {
            book.display();
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Author");
    }
}
