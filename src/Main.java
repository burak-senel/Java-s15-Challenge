import library.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);
    private static Person currentUser;

    public static void main(String[] args) {
        initializeLibrary();

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                login();
            } else {
                currentUser.displayRole();
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // newline karakterini temizle

                switch (choice) {
                    case 1:
                        if (currentUser instanceof Admin) {
                            addNewBook();
                        } else {
                            System.out.println("Yetkiniz yok.");
                        }
                        break;
                    case 2:
                        listBooks();
                        break;
                    case 3:
                        if (currentUser instanceof Librarian) {
                            borrowBook((Librarian) currentUser);
                        } else if (currentUser instanceof Reader) {
                            borrowBook((Reader) currentUser);
                        } else {
                            System.out.println("Yetkiniz yok.");
                        }
                        break;
                    case 4:
                        if (currentUser instanceof Librarian) {
                            returnBook((Librarian) currentUser);
                        } else if (currentUser instanceof Reader) {
                            returnBook((Reader) currentUser);
                        } else {
                            System.out.println("Yetkiniz yok.");
                        }
                        break;
                    case 5:
                        if (currentUser instanceof Admin) {
                            updateBook();
                        } else {
                            System.out.println("Yetkiniz yok.");
                        }
                        break;
                    case 6:
                        currentUser = null;
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Geçersiz seçim.");
                        break;
                }
            }
        }
    }

    private static void login() {
        System.out.print("Kullanıcı Adı: ");
        String name = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        // Geçici olarak admin, librarian ve reader oluşturuyoruz
        if (name.equals("admin") && password.equals("admin")) {
            currentUser = new Admin(name, password);
        } else if (name.equals("librarian") && password.equals("librarian")) {
            currentUser = new Librarian(name, password);
        } else {
            for (Reader reader : library.getReaders()) {
                if (reader.getName().equals(name) && reader.getPassword() == null) {
                    currentUser = reader;
                    return;
                }
            }
            System.out.println("Geçersiz kullanıcı adı veya şifre.");
        }
    }

    private static void displayMenu() {
        System.out.println("1. Yeni Kitap Ekle (Sadece Admin)");
        System.out.println("2. Kitapları Listele");
        System.out.println("3. Kitap Ödünç Al");
        System.out.println("4. Kitap Geri İade Et");
        System.out.println("5. Kitap Güncelle (Sadece Admin)");
        System.out.println("6. Çıkış Yap");
        System.out.println("7. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    private static void addNewBook() {
        System.out.print("Kitap Türü (Journal/StudyBook/Magazine): ");
        String type = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Yazar: ");
        String author = scanner.nextLine();
        System.out.print("Kitap İsmi: ");
        String name = scanner.nextLine();
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();
        System.out.print("Satın Alma Tarihi (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        // Tarihi işlemek için
        Date dateOfPurchase = null;
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            dateOfPurchase = new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Geçersiz tarih formatı.");
            return;
        }

        Book book;
        if (type.equalsIgnoreCase("Journal")) {
            book = new Journals(bookID, author, name, edition, dateOfPurchase);
        } else if (type.equalsIgnoreCase("StudyBook")) {
            book = new StudyBooks(bookID, author, name, edition, dateOfPurchase);
        } else if (type.equalsIgnoreCase("Magazine")) {
            book = new Magazines(bookID, author, name, edition, dateOfPurchase);
        } else {
            System.out.println("Geçersiz kitap türü.");
            return;
        }

        Admin admin = (Admin) currentUser;
        admin.addBook(book, library);
    }

    private static void listBooks() {
        for (Book book : library.getBooks()) {
            book.display();
        }
    }

    private static void borrowBook(Librarian librarian) {
        System.out.print("Kitap ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Okuyucu Adı: ");
        String readerName = scanner.nextLine();

        librarian.issueBook(bookID, readerName, library);
    }

    private static void borrowBook(Reader reader) {
        System.out.print("Kitap ID: ");
        String bookID = scanner.nextLine();
        library.lendBook(bookID, reader.getName());
    }

    private static void returnBook(Librarian librarian) {
        System.out.print("Kitap ID: ");
        String bookID = scanner.nextLine();
        Book book = library.findBookByID(bookID);
        if (book != null) {
            System.out.print("Okuyucu Adı: ");
            String readerName = scanner.nextLine();
            Reader reader = library.findReaderByName(readerName);
            if (reader != null) {
                librarian.returnBook(book, reader, library);
            }
        }
    }

    private static void returnBook(Reader reader) {
        System.out.print("Kitap ID: ");
        String bookID = scanner.nextLine();
        Book book = library.findBookByID(bookID);
        if (book != null) {
            reader.returnBook(book);
            library.takeBackBook(book, reader);
        }
    }

    private static void updateBook() {
        System.out.print("Güncellenecek Kitap ID: ");
        String bookID = scanner.nextLine();
        Book book = library.findBookByID(bookID);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        System.out.print("Yeni Yazar: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Yeni Kitap İsmi: ");
        String newName = scanner.nextLine();
        System.out.print("Yeni Baskı: ");
        String newEdition = scanner.nextLine();
        System.out.print("Yeni Satın Alma Tarihi (yyyy-MM-dd): ");
        String newDate = scanner.nextLine();

        // Tarihi işlemek için
        Date newDateOfPurchase = null;
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
            newDateOfPurchase = new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Geçersiz tarih formatı.");
            return;
        }

        book.setAuthor(newAuthor);
        book.setName(newName);
        book.setEdition(newEdition);
        book.setDateOfPurchase(newDateOfPurchase);

        System.out.println("Kitap güncellendi.");
    }

    private static void initializeLibrary() {
        // Örnek okuyucular ve kitaplar eklenir
        Admin admin = new Admin("admin", "admin");
        Librarian librarian = new Librarian("librarian", "librarian");
        Reader reader = new Reader("reader");

        library.addLibrarian(librarian);
        library.addReader(reader);

        admin.addBook(new Journals("1", "Author1", "Journal1", "1st", new Date(System.currentTimeMillis())), library);
        admin.addBook(new StudyBooks("2", "Author2", "StudyBook2", "1st", new Date(System.currentTimeMillis())), library);
        admin.addBook(new Magazines("3", "Author3", "Magazine3", "1st", new Date(System.currentTimeMillis())), library);
    }
}
