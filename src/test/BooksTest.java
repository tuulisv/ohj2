package test;

import books.Book;
import books.Books;
import books.StoreException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Books class
 */
public class BooksTest {

    @Test
    public void testGetExampleBookByIndex() throws StoreException {
        Books books = new Books();
        Book book = new Book();
        book.exampleBook();
        books.addBook(book);
        assertEquals("The Lord of the Rings", books.getBookByIndex(0).getTitle());
    }

    @Test
    public void testGetEmptyNoOfBooks() {
        Books books = new Books();
        assertEquals(0, books.getNoOfBooks());
    }

    @Test
    public void testGetNonemptyNoOfBooks() throws StoreException {
        Books books = new Books();
        Book book = new Book();
        books.addBook(book);
        assertEquals(1, books.getNoOfBooks());
    }
}
