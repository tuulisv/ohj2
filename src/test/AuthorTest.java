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
    public void testGetExampleName() {
       Author author = new Author();
       author.exampleAuthor();
       assertEquals("J. R. R. Tolkien 0", author.getName());
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
    public void testParseSetsAuthorName() {
        Author author = new Author();
        author.parse("7|Haruki Murakami");
        assertEquals("Haruki Murakami", author.getName());
    }

    @Test
    public void testAuthorToString() {
        Author.clearIdentifier();
        Author author = new Author();
        author.register();
        author.exampleAuthor();
        assertEquals("1|J. R. R. Tolkien 1", author.toString());
    }
}
