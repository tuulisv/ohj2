package test;

import books.Author;
import books.Authors;
import org.junit.Test;

import java.util.List;

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
        authors.add(author);
        assertEquals(1, authors.getNoOfAuthors());
    }

    @Test
    public void testAuthorsAreSorted() {
        Authors authors = new Authors();
        Author auth1 = new Author();
        auth1.parse("7|Haruki Murakami");
        Author auth2 = new Author();
        auth2.parse("1|J. R. R. Tolkien");
        Author auth3 = new Author();
        auth3.parse("4|Elena Ferrante");
        authors.add(auth1);
        authors.add(auth2);
        authors.add(auth3);
        assertEquals("Elena Ferrante", authors.getAuthorByIndex(0).getName());
    }

    @Test
    public void testGetAuthorById() {
        Author.clearIdentifier();
        Authors authors = new Authors();
        Author auth1 = new Author();
        Author auth2 = new Author();
        auth1.register();
        auth2.register();
        auth2.setName("J. R. R. Tolkien");
        authors.add(auth1);
        authors.add(auth2);
        assertEquals("J. R. R. Tolkien", authors.getAuthorById(2).getName());
    }

    @Test
    public void testGetAuthorsIndex() {
        Authors authors = new Authors();
        Author auth1 = new Author();
        auth1.parse("7|Haruki Murakami");
        Author auth2 = new Author();
        auth2.parse("1|J. R. R. Tolkien");
        Author auth3 = new Author();
        auth3.parse("4|Elena Ferrante");
        authors.add(auth1);
        authors.add(auth2);
        authors.add(auth3);
        assertEquals(2, authors.getIndex(1));
    }

    @Test
    public void testSearchAuthors() {
        Authors authors = new Authors();
        Author auth1 = new Author();
        auth1.parse("7|Haruki Murakami");
        Author auth2 = new Author();
        auth2.parse("1|J. R. R. Tolkien");
        Author auth3 = new Author();
        auth3.parse("4|Elena Ferrante");
        authors.add(auth1);
        authors.add(auth2);
        authors.add(auth3);
        List<Author> list = authors.search("ur");
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveAuthor() {
        Authors authors = new Authors();
        Author auth1 = new Author();
        auth1.parse("7|Haruki Murakami");
        Author auth2 = new Author();
        auth2.parse("1|J. R. R. Tolkien");
        authors.add(auth1);
        authors.add(auth2);
        authors.remove(auth1);
        assertEquals(1, authors.getNoOfAuthors());
    }
}
