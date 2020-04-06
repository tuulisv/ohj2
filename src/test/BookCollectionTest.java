package test;

import books.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        Author.clearIdentifier();
        BookCollection bc = new BookCollection();
        Author author = new Author();
        author.register();
        author.exampleAuthor();
        bc.add(author);
        assertEquals("J. R. R. Tolkien 1", bc.getAuthor(0).getName());
    }

    @Test
    public void testGetExamplePublisher() {
        Publisher.clearIdentifier();
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
        Author.clearIdentifier();
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

    @Test
    public void testSaveBooksToFile() throws IOException, StoreException {
        Book.clearIdentifier();
        Author.clearIdentifier();
        Publisher.clearIdentifier();
        String fileBooks = "testBooks";
        String fileAuthors = "testAuthors";
        String filePublishers = "testPublishers";
        String fileType = ".txt";
        File tempBooks = File.createTempFile(fileBooks, fileType);
        File tempAuthors = File.createTempFile(fileAuthors, fileType);
        File tempPublishers = File.createTempFile(filePublishers, fileType);

        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        bc.add(auth1);
        Publisher pub1 = new Publisher();
        bc.add(pub1);
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.parse("1|The Hobbit 1|The Hobbit|1|1954|1|English|1|4");
        book2.parse("2|The Hobbit 2|The Hobbit|3|1954|2|English|1|1");
        book3.parse("3|The Hobbit 3|The Hobbit|2|1954|1|English|1|3");
        bc.add(book1);
        bc.add(book2);
        bc.add(book3);

        bc.setFileNames(fileBooks, fileAuthors, filePublishers);
        bc.save();

        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileBooks), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            throw new StoreException("Error in test: " + e.getMessage());
        }

        String str = ";id|title|original title|author|publication year|publisher|language|status|rating\n" +
                "1|The Hobbit 1|The Hobbit|1|1954|1|English|1|4\n" +
                "2|The Hobbit 2|The Hobbit|3|1954|2|English|1|1\n" +
                "3|The Hobbit 3|The Hobbit|2|1954|1|English|1|3\n";

        assertEquals(str, sb.toString());
        tempBooks.delete();
        tempAuthors.delete();
        tempPublishers.delete();
    }

    @Test
    public void testSaveAuthorsToFile() throws IOException, StoreException {
        Book.clearIdentifier();
        Author.clearIdentifier();
        Publisher.clearIdentifier();
        String fileBooks = "testBooks";
        String fileAuthors = "testAuthors";
        String filePublishers = "testPublishers";
        String fileType = ".txt";
        File tempBooks = File.createTempFile(fileBooks, fileType);
        File tempAuthors = File.createTempFile(fileAuthors, fileType);
        File tempPublishers = File.createTempFile(filePublishers, fileType);

        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        Author auth2 = new Author();
        Author auth3 = new Author();
        auth1.parse("1|Donna Tartt");
        auth2.parse("2|Elena Ferrante");
        auth3.parse("3|Emily St. John Mandel");
        bc.add(auth1);
        bc.add(auth2);
        bc.add(auth3);
        Publisher pub1 = new Publisher();
        bc.add(pub1);
        Book book1 = new Book();
        bc.add(book1);

        bc.setFileNames(fileBooks, fileAuthors, filePublishers);
        bc.save();

        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileAuthors), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            throw new StoreException("Error in test: " + e.getMessage());
        }

        String str = ";authid|author\n1|Donna Tartt\n2|Elena Ferrante\n3|Emily St. John Mandel\n";

        assertEquals(str, sb.toString());
        tempBooks.delete();
        tempAuthors.delete();
        tempPublishers.delete();
    }

    @Test
    public void testSavePublishersToFile() throws IOException, StoreException {
        Book.clearIdentifier();
        Author.clearIdentifier();
        Publisher.clearIdentifier();
        String fileBooks = "testBooks";
        String fileAuthors = "testAuthors";
        String filePublishers = "testPublishers";
        String fileType = ".txt";
        File tempBooks = File.createTempFile(fileBooks, fileType);
        File tempAuthors = File.createTempFile(fileAuthors, fileType);
        File tempPublishers = File.createTempFile(filePublishers, fileType);

        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        bc.add(auth1);
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        Publisher pub3 = new Publisher();
        pub1.parse("1|Allen & Unwin");
        pub2.parse("2|Bloomsbury");
        pub3.parse("3|Alfred A. Knopf");
        bc.add(pub1);
        bc.add(pub2);
        bc.add(pub3);
        Book book1 = new Book();
        bc.add(book1);

        bc.setFileNames(fileBooks, fileAuthors, filePublishers);
        bc.save();

        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePublishers), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            throw new StoreException("Error in test: " + e.getMessage());
        }

        String str = ";pubid|publisher\n1|Allen & Unwin\n2|Bloomsbury\n3|Alfred A. Knopf\n";

        assertEquals(str, sb.toString());
        tempBooks.delete();
        tempAuthors.delete();
        tempPublishers.delete();
    }
}
