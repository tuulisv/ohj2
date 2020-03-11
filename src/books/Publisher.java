package books;

import java.io.PrintStream;

/**
 * Class for creating a Publisher object
 * @author Tuuli Veini
 * @version 1.0 11.3.2020
 */
public class Publisher {

    private int identifier;
    private String publisher;

    private static int nextIdentifier = 1;

    /**
     * Default constructor
     */
    public Publisher() {
        this.identifier = -1;
        this.publisher = "";
    }

    /**
     * Sets an example value
     */
    public void examplePublisher() {
        this.publisher = "Allen & Unwin " + this.identifier;
    }

    /**
     * Assigns the next available id for the publisher
     */
    public void register() {
        this.identifier = Publisher.nextIdentifier;
        Publisher.nextIdentifier++;
    }

    /**
     * Returns the id of the publisher
     * @return publisher id
     */
    public int getId() {
        return this.identifier;
    }

    /**
     * Returns the publisher name
     * @return publisher name
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Prints publisher information
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + " " + this.publisher);
    }
}
