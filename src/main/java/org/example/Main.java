package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import static spark.Spark.get;

public class Main {

    static Gson gson = new Gson();
    public static List<Book> books;


    public static void main(String[] args) {
        loadBooks();

        // Call appropriate filter method depending on front-end interaction
        // Set the results of the filter to a List
        // Pass that list to our get method.

        get("/books", (req, res) -> gson.toJson(books));
    }

    public static List<Book> loadBooks() {
        try (Reader reader = new FileReader("assets/books.json")) {
            Type listType = new TypeToken<List<Book>>(){}.getType();
            books = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error reading file!" + e);
        }
        return books;
    }

}

