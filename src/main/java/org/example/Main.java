package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

import static spark.Spark.get;

public class Main {

    static Gson gson = new Gson();
    public static List<Book> books;


    public static void main(String[] args) {
        loadBooks();
        // Call appropriate filter method depending on front-end interaction
        // Set the results of the filter to a List
        // Pass that list to our get method.

        filterBooksBy("author", "Malorie Blackman");
        get("/books", (req, res) -> gson.toJson(books));
    }

    public static List<Book> filterBooksBy(String field, String value) {
        List<Book> filteredList = List.of();
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
                    break;
            }
        });
        return filteredList;
    }

    public static List<Book> loadBooks() {
        try (Reader reader = new FileReader("assets/books.json")) {
            Type listType = new TypeToken<List<Book>>() {
            }.getType();
            books = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error reading file!" + e);
        }

        convertBooksToPOJO(books);

        return books;
    }

    public static List<Book> convertBooksToPOJO(List<Book> gsonBooks) {

        return null;
    }

}

