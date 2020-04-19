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
        book.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        bc.add(book);
        assertEquals("My Brilliant Friend", bc.getBook(0).getTitle());
    }

    @Test
    public void testReplaceBookWithSameId() {
        BookCollection bc = new BookCollection();
        Book book1 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        bc.replaceOrAdd(book1);
        Book book2 = new Book();
        book2.parse("5|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        bc.replaceOrAdd(book2);
        assertEquals("Station Eleven", bc.getBook(0).getTitle());
    }

    @Test
    public void testGetExampleAuthor() {
        BookCollection bc = new BookCollection();
        Author author = new Author();
        author.parse("1|J. R. R. Tolkien");
        bc.add(author);
        assertEquals("J. R. R. Tolkien", bc.getAuthor(0).getName());
    }

    @Test
    public void testGetExamplePublisher() {
        BookCollection bc = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.parse("4|Edizioni E/O");
        pub2.parse("5|Knopf");
        bc.add(pub1);
        bc.add(pub2);
        assertEquals("Knopf", bc.getPublisher(1).getName());
    }

    @Test
    public void testGetAuthorById() {
        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        Author auth2 = new Author();
        Author auth3 = new Author();
        auth1.parse("4|Elena Ferrante");
        auth2.parse("5|Emily St. John Mandel");
        auth3.parse("7|Haruki Murakami");
        bc.add(auth1);
        bc.add(auth2);
        bc.add(auth3);
        assertEquals("Haruki Murakami", bc.getAuthorById(7).getName());
    }

    @Test
    public void testGetPublisherById() {
        BookCollection bc1 = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.parse("4|Edizioni E/O");
        pub2.parse("5|Knopf");
        bc1.add(pub1);
        bc1.add(pub2);
        assertEquals("Knopf", bc1.getPublisherById(5).getName());
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
    public void testGetBookIndex() {
        BookCollection bc = new BookCollection();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.parse("8|Kafka on the Shore|海辺のカフカ|7|2002|7|Japanese|1|4");
        book2.parse("10|1Q84|1Q84|7|2009|7|Japanese|0|0");
        book3.parse("12|Dance Dance Dance|ダンス・ダンス・ダンス|7|1988|14|Japanese|1|5");
        bc.add(book1);
        bc.add(book2);
        bc.add(book3);
        assertEquals(2, bc.getBookIndex(book2));
    }

    @Test
    public void testRemoveOneOfAuthorsBooks() {
        BookCollection bc = new BookCollection();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.parse("8|Kafka on the Shore|海辺のカフカ|7|2002|7|Japanese|1|4");
        book2.parse("10|1Q84|1Q84|7|2009|7|Japanese|0|0");
        book3.parse("12|Dance Dance Dance|ダンス・ダンス・ダンス|7|1988|14|Japanese|1|5");
        bc.add(book1);
        bc.add(book2);
        bc.add(book3);
        bc.remove(book1);
        assertEquals(2, bc.getAuthorsWorks(7).size());
    }

    @Test
    public void testRemovingAuthorsBooksRemovesAuthor() {
        BookCollection bc = new BookCollection();
        Author auth1 = new Author();
        Author auth2 = new Author();
        auth1.parse("4|Elena Ferrante");
        auth2.parse("5|Emily St. John Mandel");
        bc.add(auth1);
        bc.add(auth2);
        Book book1 = new Book();
        Book book2 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        book2.parse("6|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        bc.add(book1);
        bc.add(book2);
        bc.remove(book1);
        assertEquals(1, bc.getNoOfAuthors());
    }

    @Test
    public void testRemovingPublishersBooksRemovesPublisher() {
        BookCollection bc = new BookCollection();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.parse("4|Edizioni E/O");
        pub2.parse("5|Knopf");
        bc.add(pub1);
        bc.add(pub2);
        Book book1 = new Book();
        Book book2 = new Book();
        book1.parse("5|My Brilliant Friend|L'amica geniale|4|2011|4|Italian|1|4");
        book2.parse("6|Station Eleven|Station Eleven|5|2014|5|English|0|0");
        bc.add(book1);
        bc.add(book2);
        bc.remove(book1);
        assertEquals("Knopf", bc.getPublisher(0).getName());
    }

    @Test
    public void testSaveBooksToFile() throws IOException, StoreException {
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
    }

    @Test
    public void testSaveAuthorsToFile() throws IOException, StoreException {
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
    }

    @Test
    public void testSavePublishersToFile() throws IOException, StoreException {
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
    }
}
