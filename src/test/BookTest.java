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
    public void testGetDefaultTitle() {
        Book book = new Book();
        assertEquals("", book.getTitle());
    }

    @Test
    public void testGetExampleTitle() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals("My Brilliant Friend", book.getTitle());
    }

    @Test
    public void testGetExampleOrigTitle() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals("L'amica geniale", book.getOrigTitle());
    }

    @Test
    public void testGetDefaultAuthorId() {
        Book book = new Book();
        assertEquals(-1, book.getAuthorId());
    }

    @Test
    public void testGetExampleAuthorId() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals(4, book.getAuthorId());
    }

    @Test
    public void testGetExamplePubYear() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals(2011, book.getPubYear());
    }

    @Test
    public void testGetDefaultPubIndex() {
        Book book = new Book();
        assertEquals(-1, book.getPubId());
    }

    @Test
    public void testGetExamplePubId() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals(4, book.getPubId());
    }

    @Test
    public void testGetExampleLanguage() {
        Book book = new Book();
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        assertEquals("Italian", book.getLanguage());
    }

    @Test
    public void testGetDefaultStatus() {
        Book book = new Book();
        assertEquals(0, book.getStatus());
    }

    @Test
    public void testRegisteringGrowsNextId() {
        Book.clearIdentifier();
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

    @Test
    public void testSetTitle() {
        Book book = new Book();
        book.setTitle("The Lord of the Rings");
        assertEquals("The Lord of the Rings", book.getTitle());
    }

    @Test
    public void testSetPubYear() {
        Book book = new Book();
        book.setPubYear(1954);
        assertEquals(1954, book.getPubYear());
    }

    @Test
    public void testOlderBookIsSmaller() {
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setPubYear(1999);
        book2.setPubYear(1979);
        assertEquals(20, book1.compareTo(book2));
    }

    @Test
    public void testBookToString() {
        Book book = new Book();
        book.parse("8|Kafka on the Shore|海辺のカフカ|7|2002|7|Japanese|1|4");
        assertEquals("8|Kafka on the Shore|海辺のカフカ|7|2002|7|Japanese|1|4",
                     book.toString());
    }
}
