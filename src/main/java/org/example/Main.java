package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static spark.Spark.get;

public class Main {

    static Gson gson = new Gson();
    public static ArrayList<Book> books;
    public static ArrayList<Person> people;

    public static void main(String[] args) {
        loadBooks();
        loadPeople();

        // By using the get method below, the REST service returns the appropriate json according to the filter.

        /* Go to:
         * http://localhost:4567/books
         * while the program is running to view what is returned by the service
         */

        // Here are some examples (comment/uncomment as appropriate)
//        get("/books", (req, res) -> gson.toJson(books));
        get("/books", (req, res) -> gson.toJson(filterByAvailable()));
//        get("/books", (req, res) -> gson.toJson(filterByDueToReturn()));
//        get("/books", (req, res) -> gson.toJson(filterBooksBy("author", "Malorie Blackman")));
//        get("/books", (req, res) -> gson.toJson(filterBooksBy("title", "Smashed")));
    }

    public static ArrayList<Book> filterBooksBy(String field, String value) {
        ArrayList<Book> filteredList = new ArrayList<>();
        books.forEach(book -> {
            switch (field) {
                case "title":
                    if (Objects.equals(book.getTitle(), value))
                        filteredList.add(book);
                    break;
                case "author":
                    if (Objects.equals(book.getAuthor(), value))
                        filteredList.add(book);
                    break;
                /*
                 * Add other cases here, using the appropriate getter for each one.
                 */
                default:
                    // Feedback that the field is invalid
                    break;
            }
        });
        return filteredList;
    }

    private static LocalDate convertToCustomDateFormat(String date) {
        // LocalDate.now() is yyyy-MM-dd whereas books.json uses dd-MM-yyyy, so this method is used to enable comparison.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, format);
    }

    public static ArrayList<Book> filterByDueToReturn() {
        ArrayList<Book> filteredList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        books.forEach(book -> {
            if (
                    !Objects.equals(book.getReturn_date(), "") &&
                            convertToCustomDateFormat(book.getReturn_date()).isBefore(today)
            ) {
                filteredList.add(book);
            }
        });
        return filteredList;
    }

    public static ArrayList<Book> filterByAvailable() {
        ArrayList<Book> filteredList = new ArrayList<>();
        books.forEach(book -> {
            if (Objects.equals(book.getLoaned_by_id(), "")) {
                filteredList.add(book);
            }
        });
        return filteredList;
    }

    public static ArrayList<Person> loadPeople() {
        try (Reader reader = new FileReader("assets/people.json")) {
            Type listType = new TypeToken<List<Person>>() {
            }.getType();
            people = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error reading file!" + e);
        }
        return people;
    }

    public static ArrayList<Book> loadBooks() {
        try (Reader reader = new FileReader("assets/books.json")) {
            Type listType = new TypeToken<List<Book>>() {
            }.getType();
            books = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error reading file!" + e);
        }
        return books;
    }

}

