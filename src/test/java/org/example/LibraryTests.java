package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class LibraryTests {

    @DisplayName("Load all books")
    @Test
    void test_loadBooks() {
        List<Book> test_books = Main.loadBooks();
        assertEquals(15, test_books.size());
    }

    @DisplayName("Load all people")
    @Test
    void test_loadPeople() {
        List<Person> test_people = Main.loadPeople();
        assertEquals(3, test_people.size());
    }

    @DisplayName("Filter by author")
    @Test
    void test_filterBooksByAuthor() {
        Main.loadBooks();
        ArrayList<Book> filteredList = Main.filterBooksBy("author", "Malorie Blackman");
        assertEquals(4, filteredList.size());
        filteredList.forEach(book -> {
                    assertEquals("Malorie Blackman", book.getAuthor());
                }
        );
    }

    @DisplayName("Filter by due date")
    @Test
    void test_filterBooksByDue() {
        Main.loadBooks();
        ArrayList<Book> filteredList = Main.filterByDueToReturn();
        assertEquals(7, filteredList.size());
        // If the front end doesn't want the whole json list, specific values can be returned
        // e.g. the expected return date
        filteredList.forEach(book -> {
            // Return book.getReturn_date() for front end to use however they like
        });
    }

    @DisplayName("Filter by available")
    @Test
    void test_filterByAvailable() {
        Main.loadBooks();
        ArrayList<Book> filteredList = Main.filterByAvailable();
        assertEquals(3, filteredList.size());
    }

    @DisplayName("Filter by due date and get loaner's details")
    @Test
    void test_filterDueByWho() {
        Main.loadBooks();
        ArrayList<Book> filteredList = Main.filterByDueToReturn();
        // Reader details can be accessed through the filtered list:
        filteredList.forEach(book -> {
            Main.loadPeople().stream()
                    .filter(person -> {
                        assertSame(person.getId(), book.getLoaned_by_id());
                        // Do something with the person...
                        return false;
                    })
            ;
        });
    }
}