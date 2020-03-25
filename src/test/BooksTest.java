package test;

import books.Author;
import books.Book;
import books.Books;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Books class
 */
public class BooksTest {

    @Test
    public void testGetExampleBookByIndex() {
        Books books = new Books();
        Book book = new Book();
        book.exampleBook();
        books.add(book);
        assertEquals("The Lord of the Rings 0", books.getBookByIndex(0).getTitle());
    }

    @Test
    public void testGetEmptyNoOfBooks() {
        Books books = new Books();
        assertEquals(0, books.getNoOfBooks());
    }

    @Test
    public void testGetNonemptyNoOfBooks() {
        Books books = new Books();
        Book book = new Book();
        books.add(book);
        assertEquals(1, books.getNoOfBooks());
    }

    @Test
    public void testArraySizeGrows() {
        Books books = new Books();
        for (int i = 0; i < 15; i++) {
            Book book = new Book();
            books.add(book);
        }

        assertEquals(15, books.getNoOfBooks());
    }

    @Test
    public void testGetAuthorsWorks() {
        Books books = new Books();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.register();
        author2.register();
        Book book1 = new Book(1, 3);
        Book book2 = new Book(1, 2);
        Book book3 = new Book(2, 3);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> works1 = books.getAuthorsWorks(author1);
        assertEquals(2, works1.size());
    }

    @Test
    public void testGetEmptyAuthorsWorks() {
        Books books = new Books();
        Author author = new Author();
        List<Book> works = books.getAuthorsWorks(author);
        assertEquals(0, works.size());
    }
}
