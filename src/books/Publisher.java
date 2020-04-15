package books;

import fi.jyu.mit.ohj2.Mjonot;

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
    public String getName() {
        return this.publisher;
    }

    /**
     * Sets the publisher id and grows next id if necessary
     * @param id set id
     */
    private void setIdentifier(int id) {
        this.identifier = id;
        if (this.identifier >= Publisher.nextIdentifier) Publisher.nextIdentifier = this.identifier + 1;
    }

    /**
     * Sets publisher name
     * @param publisher publisher name
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Resets the id to 1
     */
    public static void clearIdentifier(){
        Publisher.nextIdentifier = 1;
    }

    /**
     * Parse author information from the line
     * @param line
     */
    public void parse(String line) {
        StringBuilder sb = new StringBuilder(line);
        setIdentifier(Mjonot.erota(sb, '|', this.identifier));
        this.publisher = Mjonot.erota(sb, '|', this.publisher);
    }

    /**
     * Returns publisher id and name as string
     * @return publisher id and name
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier).append("|").append(this.publisher);
        return sb.toString();
    }

    /**
     * Returns publisher name as string
     * @return publisher as string
     */
    @Override
    public String toString() {
        return this.publisher;
    }
}
