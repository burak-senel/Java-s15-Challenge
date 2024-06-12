package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Reader> readers;
    private List<Librarian> librarians;

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
        librarians = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (findBookByID(book.getBookID()) != null) {
            System.out.println("Aynı ID'ye sahip başka bir kitap zaten mevcut.");
            return;
        }
        books.add(book);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public Book findBookByID(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public Reader findReaderByName(String readerName) {
        for (Reader reader : readers) {
            if (reader.getName().equals(readerName)) {
                return reader;
            }
        }
        return null;
    }

    public void lendBook(String bookID, String readerName) {
        Book book = findBookByID(bookID);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        Reader reader = findReaderByName(readerName);
        if (reader == null) {
            System.out.println("Okuyucu bulunamadı.");
            return;
        }

        if (book.getStatus().equals("available") && reader.getBooks().size() < 5) {
            reader.borrowBook(book);
            book.updateStatus("borrowed");
            System.out.println("Kitap ödünç alındı.");
        } else {
            System.out.println("Kitap ödünç verilemedi.");
        }
    }

    public void takeBackBook(Book book, Reader reader) {
        reader.returnBook(book);
        book.updateStatus("available");
    }
}
