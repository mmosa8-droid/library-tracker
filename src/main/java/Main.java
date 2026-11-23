import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Path BOOKS_FILE = Path.of("books.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type BOOK_LIST_TYPE = new TypeToken<List<Book>>() {}.getType();
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        print_banner();
        List<Book> books = loadBooks();
        while (true) {
            System.out.println("\n1) Add book  2) List books  3) Search  4) Remove  0) Exit");
            System.out.print("Choose: ");
            String choice = INPUT.nextLine().trim();
            switch (choice) {
                case "1" -> addBook(books);
                case "2" -> listBooks(books);
                case "3" -> searchBooks(books);
                case "4" -> removeBook(books);
                case "0" -> { saveBooks(books); System.out.println("Goodbye!"); return; }
                default -> System.out.println("Please choose 0, 1, 2, 3, or 4.");
            }
        }
    }

    static void print_banner() {
        System.out.println("======================");
        System.out.println("    LIBRARY TRACKER");
        System.out.println("======================");
    }

    private static List<Book> loadBooks() {
        try {
            if (!Files.exists(BOOKS_FILE)) {
                Files.writeString(BOOKS_FILE, "[]");
            }
            String json = Files.readString(BOOKS_FILE);
            List<Book> loaded = GSON.fromJson(json, BOOK_LIST_TYPE);
            return loaded == null ? new ArrayList<>() : loaded;
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not read books.json; starting with an empty list.");
            return new ArrayList<>();
        }
    }

    private static void saveBooks(List<Book> books) {
        try {
            Files.writeString(BOOKS_FILE, GSON.toJson(books));
        } catch (IOException e) {
            System.out.println("Could not save books: " + e.getMessage());
        }
    }

    private static void addBook(List<Book> books) {
        System.out.print("Title: ");
        String title = INPUT.nextLine().trim();
        System.out.print("Author: ");
        String author = INPUT.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        int nextId = books.stream().mapToInt(b -> b.id).max().orElse(0) + 1;
        books.add(new Book(nextId, title, author));
        saveBooks(books);
        System.out.println("Book added.");
    }

    private static void listBooks(List<Book> books) {
        if (books.isEmpty()) { System.out.println("No books yet."); return; }
        System.out.println("Books (" + books.size() + "):");
        books.forEach(System.out::println);
    }

    private static void searchBooks(List<Book> books) {
        System.out.print("Search title or author: ");
        String query = INPUT.nextLine().trim().toLowerCase();
        if (query.isEmpty()) { System.out.println("Enter a search term."); return; }
        List<Book> matches = books.stream()
                .filter(b -> b.title.toLowerCase().contains(query) || b.author.toLowerCase().contains(query))
                .toList();
        if (matches.isEmpty()) System.out.println("No matching books for \"" + query + "\".");
        else matches.forEach(System.out::println);
    }

    private static void removeBook(List<Book> books) {
        System.out.print("Book ID to remove: ");
        try {
            int id = Integer.parseInt(INPUT.nextLine().trim());
            boolean removed = books.removeIf(b -> b.id == id);
            if (removed) { saveBooks(books); System.out.println("Book removed successfully."); }
            else System.out.println("No book found with that ID.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
        }
    }

    static class Book {
        int id;
        String title;
        String author;
        Book(int id, String title, String author) { this.id = id; this.title = title; this.author = author; }
        @Override public String toString() { return id + " | " + title + " | " + author; }
    }
}
