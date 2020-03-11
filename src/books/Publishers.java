package books;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles having several publishers in the program
 *
 * @author Tuuli Veini
 * @version 1.0 11.3.2020
 */
public class Publishers {

    private List<Publisher> publishers;

    /**
     * Default constructor
     */
    public Publishers() {
        this.publishers = new ArrayList<>();
    }

    /**
     * Adds a new publisher
     * @param publisher added publisher
     */
    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    /**
     * Returns the number of publishers
     * @return number of publishers
     */
    public int getNoOfPublishers() {
        return this.publishers.size();
    }

    /**
     * Returns the publisher corresponding to the id
     * @param id id of the publisher
     * @return publisher
     */
    public Publisher getPublisherById(int id) {
        for (Publisher pub: this.publishers) {
            if (pub.getId() == id) {
                return pub;
            }
        }

        return new Publisher();
    }

    /**
     * Returns the publisher at the given index
     * @param index index of the publisher
     * @return publisher at index
     */
    public Publisher getPublisherByIndex(int index) {
        for (int i = 0; i < getNoOfPublishers(); i++) {
            if (i == index) return this.publishers.get(i);
        }

        return new Publisher();
    }
}
