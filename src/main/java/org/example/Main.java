package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static spark.Spark.get;

public class Main {

    static Gson gson = new Gson();
    public static ArrayList<Book> books;
    public static ArrayList<Person> people;

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
                default:
                    // Feedback that the field is invalid
                    break;
            }
        });
        return filteredList;
    }

    private static LocalDate convertToCustomDateFormat(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, format);
    }

    public static ArrayList<Book> filterByDueToReturn() {
        ArrayList<Book> filteredList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        books.forEach(book -> {
           if (
                   !Objects.equals(book.getReturn_date(), "") &&
                   convertToCustomDateFormat(book.getReturn_date()).isAfter(today)
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

