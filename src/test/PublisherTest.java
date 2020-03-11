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
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.register();
        pub2.register();
        assertEquals(2, pub2.getId());
    }
}
