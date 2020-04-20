package test;

import books.Publisher;
import books.Publishers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Publishers class
 */
public class PublishersTest {

    @Test
    public void testGetEmptyNoOfPublishers() {
        Publishers pub = new Publishers();
        assertEquals(0, pub.getNoOfPublishers());
    }

    @Test
    public void testGetNonemptyNoOfPublishers() {
        Publishers pub = new Publishers();
        Publisher p1 = new Publisher();
        Publisher p2 = new Publisher();
        pub.add(p1);
        pub.add(p2);
        assertEquals(2, pub.getNoOfPublishers());
    }

    @Test
    public void testGetPublisherById() {
        Publisher.clearIdentifier();
        Publishers pub = new Publishers();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.parse("4|Edizioni E/O");
        pub2.parse("5|Knopf");
        pub.add(pub1);
        pub.add(pub2);
        assertEquals("Knopf", pub.getPublisherById(5).getName());
    }

    @Test
    public void testGetNonexistentPublisher() {
        Publisher.clearIdentifier();
        Publishers pub = new Publishers();
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();
        pub1.parse("4|Edizioni E/O");
        pub2.parse("5|Knopf");
        assertEquals("", pub.getPublisherById(6).getName());
    }

    @Test
    public void testGetPublisherByIndex() {
        Publisher.clearIdentifier();
        Publishers pub = new Publishers();
        Publisher p1 = new Publisher();
        Publisher p2 = new Publisher();
        p1.parse("4|Edizioni E/O");
        p2.parse("5|Knopf");
        pub.add(p1);
        pub.add(p2);
        assertEquals("Knopf", pub.getPublisherByIndex(1).getName());
    }

    @Test
    public void testRemovePublisher() {
        Publisher.clearIdentifier();
        Publishers pub = new Publishers();
        Publisher p1 = new Publisher();
        p1.parse("4|Edizioni E/O");
        pub.add(p1);
        pub.remove(p1);
        assertEquals(0, pub.getNoOfPublishers());
    }
}
