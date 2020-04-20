package test;

import books.Author;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Author class
 */
public class AuthorTest {

    @Test
    public void testGetDefaultName() {
        Author author = new Author();
        assertEquals("", author.getName());
    }

    @Test
    public void testRegisteringGrowsId() {
        Author.clearIdentifier();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.register();
        author2.register();
        assertEquals(2, author2.getId());
    }

    @Test
    public void testSetAuthorName() {
        Author author = new Author();
        author.setName("Elena Ferrante");
        assertEquals("Elena Ferrante", author.getName());
    }

    @Test
    public void testParseSetsAuthorName() {
        Author.clearIdentifier();
        Author author = new Author();
        author.parse("7|Haruki Murakami");
        assertEquals("Haruki Murakami", author.getName());
    }

    @Test
    public void testParseGrowsNextId() {
        Author.clearIdentifier();
        Author author1 = new Author();
        author1.parse("4|Elena Ferrante");
        Author author2 = new Author();
        author2.register();
        assertEquals(5, author2.getId());
    }

    @Test
    public void testAuthorToString() {
        Author.clearIdentifier();
        Author author = new Author();
        author.parse("4|Elena Ferrante");
        assertEquals("Elena Ferrante", author.toString());
    }

    @Test
    public void testPrintAuthor() {
        Author.clearIdentifier();
        Author author = new Author();
        author.parse("4|Elena Ferrante");
        assertEquals("4|Elena Ferrante", author.print());
    }

    @Test
    public void testCompareDifferentAuthors() {
        Author.clearIdentifier();
        Author author1 = new Author();
        author1.parse("4|Elena Ferrante");
        Author author2 = new Author();
        author2.parse("7|Haruki Murakami");
        assertEquals(-3, author1.compareTo(author2));
    }

    @Test
    public void testCompareSameAuthors() {
        Author.clearIdentifier();
        Author author1 = new Author();
        author1.parse("4|Elena Ferrante");
        Author author2 = new Author();
        author2.parse("5|Elena Ferrante");
        assertEquals(0, author1.compareTo(author2));
    }
}
