package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTests {

    @DisplayName("Load all books")
    @Test
    void test_loadBooks() {
        List<Book> test_books = Main.loadBooks();
        assertEquals(15, test_books.size());
    }

    @DisplayName("Load all books")
    @ParameterizedTest
    @ValueSource(strings = {"author", "Malorie Blackman"})
    void test_filterBooksByAuthor(String field, String value) {
        List<Book> test_books = Main.loadBooks();
        Main.filterBooksBy(field, value);
        assertEquals(3, test_books.size());
        test_books.forEach(book -> {
                    assertEquals("Malorie Blackman", book.getAuthor());
                }
        );
    }

}