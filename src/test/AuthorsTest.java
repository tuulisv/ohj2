package test;

import books.Author;
import books.Authors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Authors class
 */
public class AuthorsTest {

    @Test
    public void testGetEmptyNoOfAuthors() {
        Authors authors = new Authors();
        assertEquals(0, authors.getNoOfAuthors());
    }

    @Test
    public void testGetNonemptyNoOfAuthors() {
        Authors authors = new Authors();
        Author author = new Author();
        authors.addAuthor(author);
        assertEquals(1, authors.getNoOfAuthors());
    }

    @Test
    public void testGetAuthorById() {
        Authors authors = new Authors();
        Author auth1 = new Author();
        Author auth2 = new Author();
        auth1.register();
        auth2.register();
        auth2.exampleAuthor();
        authors.addAuthor(auth1);
        authors.addAuthor(auth2);
        assertEquals("J. R. R. Tolkien 2", authors.getAuthorById(2).getName());
    }

    @Test
    public void testGetAuthorByIndex() {
        Author.clearIdentifier();
        Authors a = new Authors();
        Author a1 = new Author();
        Author a2 = new Author();
        a1.register();
        a2.register();
        a.addAuthor(a1);
        a.addAuthor(a2);
        assertEquals(1, a.getAuthorByIndex(0).getId());
    }
}
