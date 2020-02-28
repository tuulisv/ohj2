package test;

import books.Book;
import books.BookCollection;
import books.StoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for BookCollection class
 */
public class BookCollectionTest {

    @Test
    public void testGetExampleBook() throws StoreException {
        BookCollection bc = new BookCollection();
        Book book = new Book();
        book.exampleBook();
        bc.addBook(book);
        assertEquals("The Lord of the Rings", bc.getBook(0).getTitle());
    }

    @Test
    public void testGetEmptyNoOfBooks() {
        BookCollection bc = new BookCollection();
        assertEquals(0, bc.getNoOfBooks());
    }

    @Test
    public void testGetNonemptyNoOfBooks() throws StoreException {
        BookCollection bc = new BookCollection();
        Book book = new Book();
        bc.addBook(book);
        assertEquals(1, bc.getNoOfBooks());
    }
}
