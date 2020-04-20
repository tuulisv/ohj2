package test;

import books.Publisher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Publisher class
 */
public class PublisherTest {

    @Test
    public void testGetDefaultName() {
        Publisher pub = new Publisher();
        assertEquals("", pub.getName());
    }

    @Test
    public void testRegisteringGrowsId() {
        Publisher.clearIdentifier();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.register();
        pub2.register();
        assertEquals(2, pub2.getId());
    }

    @Test
    public void testSetPublisherName() {
        Publisher pub = new Publisher();
        pub.setName("Edizioni E/O");
        assertEquals("Edizioni E/O", pub.getName());
    }

    @Test
    public void testParseSetsPublisherName() {
        Publisher.clearIdentifier();
        Publisher pub = new Publisher();
        pub.parse("4|Edizioni E/O");
        assertEquals("Edizioni E/O", pub.getName());
    }

    @Test
    public void testParseGrowsNextId() {
        Publisher.clearIdentifier();
        Publisher pub1 = new Publisher();
        pub1.parse("14|Kodansha");
        Publisher pub2 = new Publisher();
        pub2.register();
        assertEquals(15, pub2.getId());
    }

    @Test
    public void testPublisherToString() {
        Publisher.clearIdentifier();
        Publisher pub = new Publisher();
        pub.parse("4|Edizioni E/O");
        assertEquals("Edizioni E/O", pub.toString());
    }

    @Test
    public void testPrintPublisher() {
        Publisher.clearIdentifier();
        Publisher pub = new Publisher();
        pub.parse("4|Edizioni E/O");
        assertEquals("4|Edizioni E/O", pub.print());
    }
}
