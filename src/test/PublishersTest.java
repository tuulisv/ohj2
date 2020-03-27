package test;

import books.Publisher;
import books.Publishers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        pub1.register();
        pub2.register();
        pub1.examplePublisher();
        pub2.examplePublisher();
        pub.add(pub1);
        pub.add(pub2);
        assertEquals("Allen & Unwin 2", pub.getPublisherById(2).getName());
    }

    @Test
    public void testGetPublisherByIndex() {
        Publisher.clearIdentifier();
        Publishers pub = new Publishers();
        Publisher p1 = new Publisher();
        Publisher p2 = new Publisher();
        p1.register();
        p2.register();
        p1.examplePublisher();
        p2.examplePublisher();
        pub.add(p1);
        pub.add(p2);
        assertEquals("Allen & Unwin 2", pub.getPublisherByIndex(1).getName());
    }
}
