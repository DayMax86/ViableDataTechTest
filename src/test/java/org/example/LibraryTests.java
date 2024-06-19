package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTests {

    @DisplayName("Load all books")
    @Test
    void test_loadBooks() {
        List<Book> test_books = Main.loadBooks();
        assertEquals(15, test_books.size());
    }

}