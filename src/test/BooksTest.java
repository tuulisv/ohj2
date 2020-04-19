package test;

import books.Author;
import books.Book;
import books.Books;
import books.Publisher;
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
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        books.add(book);
        assertEquals("My Brilliant Friend", books.getBookByIndex(0).getTitle());
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
    public void testGetBookAtIndexI() {
        Books books = new Books();
        Book book1 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        Book book2 = new Book();
        book2.parse("6|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        books.add(book1);
        books.add(book2);
        assertEquals("Station Eleven", books.getBookByIndex(1).getTitle());
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
    public void testBookWithSameIdIsReplaced() {
        Books books = new Books();
        Book book1 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|0|0");
        Book book2 = new Book();
        book2.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|5");
        books.replaceOrAdd(book1);
        books.replaceOrAdd(book2);
        assertEquals(5, books.getBookByIndex(0).getRating());
    }

    @Test
    public void testGetBookIndex() {
        Books books = new Books();
        Book book1 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        Book book2 = new Book();
        book2.parse("6|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        Book book3 = new Book();
        book3.parse("8|Kafka on the Shore|海辺のカフカ|7|2002|7|Japanese|1|4");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        assertEquals(2, books.getIndex(8));
    }

    @Test
    public void testGetIndexForBookNotInList() {
        Books books = new Books();
        Book book1 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        Book book2 = new Book();
        book2.parse("6|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        assertEquals(-1, books.getIndex(8));
    }

    @Test
    public void testGetAuthorsWorks() {
        Author.clearIdentifier();
        Books books = new Books();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.register();
        author2.register();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.setAuthorId(1);
        book2.setAuthorId(2);
        book3.setAuthorId(1);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> works1 = books.getAuthorsWorks(author1.getId());
        assertEquals(2, works1.size());
    }

    @Test
    public void testGetEmptyAuthorsWorks() {
        Books books = new Books();
        Author author = new Author();
        List<Book> works = books.getAuthorsWorks(author.getId());
        assertEquals(0, works.size());
    }

    @Test
    public void testGetPublishersBooks() {
        Publisher.clearIdentifier();
        Books books = new Books();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.register();
        pub2.register();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.setPubId(1);
        book2.setPubId(2);
        book3.setPubId(1);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> books1 = books.getPublishersBooks(pub1.getId());
        assertEquals(2, books1.size());
    }
}
