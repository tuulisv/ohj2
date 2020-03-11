package test;

import books.Author;
import books.Book;
import books.BookCollection;
import books.Publisher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for BookCollection class
 */
public class BookCollectionTest {

    @Test
    public void testGetExampleBook() {
        BookCollection bc = new BookCollection();
        Book book = new Book();
        book.exampleBook();
        bc.add(book);
        assertEquals("The Lord of the Rings 0", bc.getBook(0).getTitle());
    }

    @Test
    public void testGetExampleAuthor() {
        BookCollection bc = new BookCollection();
        Author author = new Author();
        author.register();
        author.exampleAuthor();
        bc.add(author);
        assertEquals("J. R. R. Tolkien 1", bc.getAuthor(0).getName());
    }

    @Test
    public void testGetExamplePublisher() {
        BookCollection bc = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.register();
        pub2.register();
        pub1.examplePublisher();
        pub2.examplePublisher();
        bc.add(pub1);
        bc.add(pub2);
        assertEquals("Allen & Unwin 2", bc.getPublisher(1).getName());
    }

    @Test
    public void testGetAuthorById() {
        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        Author auth2 = new Author();
        Author auth3 = new Author();
        auth1.register();
        auth2.register();
        auth3.register();
        auth1.exampleAuthor();
        auth2.exampleAuthor();
        auth3.exampleAuthor();
        bc.add(auth1);
        bc.add(auth2);
        bc.add(auth3);
        assertEquals("J. R. R. Tolkien 2", bc.getAuthorById(2).getName());
    }

    @Test
    public void testGetPublisherById() {
        Publisher.clearIdentifier();
        BookCollection bc1 = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.register();
        pub2.register();
        pub1.examplePublisher();
        pub2.examplePublisher();
        bc1.add(pub1);
        bc1.add(pub2);
        assertEquals("Allen & Unwin 2", bc1.getPublisherById(2).getName());
    }

    @Test
    public void testGetEmptyNoOfBooks() {
        BookCollection bc = new BookCollection();
        assertEquals(0, bc.getNoOfBooks());
    }

    @Test
    public void testGetNonemptyNoOfBooks() {
        BookCollection bc = new BookCollection();
        Book book = new Book();
        bc.add(book);
        assertEquals(1, bc.getNoOfBooks());
    }

    @Test
    public void testGetEmptyNoOfAuthors() {
        BookCollection bc = new BookCollection();
        assertEquals(0, bc.getNoOfAuthors());
    }

    @Test
    public void testGetNonemptyNoOfAuthors() {
        BookCollection bc = new BookCollection();
        Author author1 = new Author();
        Author author2 = new Author();
        bc.add(author1);
        bc.add(author2);
        assertEquals(2, bc.getNoOfAuthors());
    }

    @Test
    public void testGetEmptyNoOfPublishers() {
        BookCollection bc = new BookCollection();
        assertEquals(0, bc.getNoOfPublishers());
    }

    @Test
    public void testGetNonemptyNoOfPublishers() {
        BookCollection bc = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        bc.add(pub1);
        bc.add(pub2);
        assertEquals(2, bc.getNoOfPublishers());
    }
}
