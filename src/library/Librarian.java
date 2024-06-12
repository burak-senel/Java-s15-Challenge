package library;

public class Librarian extends Person {

    public Librarian(String name, String password) {
        super(name, password);
    }

    public void issueBook(String bookID, String readerName, Library library) {
        library.lendBook(bookID, readerName);
    }

    public void returnBook(Book book, Reader reader, Library library) {
        library.takeBackBook(book, reader);
    }

    public void displayRole() {
        System.out.println("Role: Librarian");
    }
}
