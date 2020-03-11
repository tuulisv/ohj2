package test;

import books.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Book class
 */
public class BookTest {

    @Test
    public void testGetDefaultId() {
        Book book = new Book();
        assertEquals(0, book.getId());
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
        assertEquals("The Lord of the Rings 0", book.getTitle());
    }

    @Test
    public void testGetExampleOrigTitle() {
        Book book = new Book();
        book.exampleBook();
        assertEquals("The Lord of the Rings", book.getOrigTitle());
    }

    @Test
    public void testGetDefaultAuthorId() {
        Book book = new Book();
        assertEquals(-1, book.getAuthorId());
    }

    @Test
    public void testGetGivenAuthorId() {
        Book book = new Book(5, 3);
        assertEquals(5, book.getAuthorId());
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
        assertEquals(-1, book.getPubId());
    }

    @Test
    public void testGetGivenPubId() {
        Book book = new Book(5, 3);
        assertEquals(3, book.getPubId());
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
    public void testRegisteringGrowsNextId() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.register();
        book2.register();
        book3.register();
        assertEquals(3, book3.getId());
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
