package test;

import books.Book;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Book class
 */
public class BookTest {

    @Test
    public void testGetDefaultIdentifier() {
        Book book = new Book();
        assertEquals(-1, book.getIdentifier());
    }

    @Test
    public void testGetDefaultBookTitle() {
        Book book = new Book();
        assertEquals("", book.getTitle());
    }

    @Test
    public void testGetExampleTitle() {
        Book book = new Book();
        book.exampleBook();
        assertEquals("The Lord of the Rings", book.getTitle());
    }

    @Test
    public void testGetExampleOrigTitle() {
        Book book = new Book();
        book.exampleBook();
        assertEquals("The Lord of the Rings", book.getOrigTitle());
    }

    @Test
    public void testGetDefaultAuthorIndex() {
        Book book = new Book();
        assertEquals(-1, book.getAuthorIndex());
    }

    @Test
    public void testGetExamplePubYear() {
        Book book = new Book();
        book.exampleBook();
        assertEquals(1954, book.getPubYear());
    }

    @Test
    public void testGetDefaultPubIndex() {
        Book book = new Book();
        assertEquals(-1, book.getPubIndex());
    }

    @Test
    public void testGetExampleLanguage() {
        Book book = new Book();
        book.exampleBook();
        assertEquals("English", book.getLanguage());
    }

    @Test
    public void testGetDefaultStatus() {
        Book book = new Book();
        assertEquals(false, book.getStatus());
    }

    @Test
    public void testGetExampleRatings() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();
        Book book5 = new Book();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book: books) {
            book.exampleBook();
            assertTrue(book.getRating() >= 0 && book.getRating() <= 5);
        }
    }
}
