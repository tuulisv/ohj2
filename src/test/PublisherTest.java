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
    public void testGetExampleName() {
        Publisher pub = new Publisher();
        pub.examplePublisher();
        assertEquals("Allen & Unwin 0", pub.getName());
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
    public void testParseSetsPublisherName() {
        Publisher pub = new Publisher();
        pub.parse("4|Edizioni E/O");
        assertEquals("Edizioni E/O", pub.getName());
    }

    @Test
    public void testPublisherToString() {
        Publisher.clearIdentifier();
        Publisher pub = new Publisher();
        pub.register();
        pub.examplePublisher();
        assertEquals("1|Allen & Unwin 1", pub.toString());
    }
}
