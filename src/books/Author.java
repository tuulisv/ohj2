package books;

import java.io.PrintStream;

/**
 * Class for creating an Author object
 *
 * @author Tuuli Veini
 * @version 1.0 26.2.2020
 */
public class Author {

    private int identifier;
    private String author;

    private static int nextIdentifier;

    /**
     * Default constructor
     */
    public Author() {
        this.identifier = -1;
        this.author = "";
    }

    /**
     * Sets test values
     */
    public void exampleAuthor() {
        this.identifier = 1;
        this.author = "J. R. R. Tolkien";
        Author.nextIdentifier = 2;
    }

    /**
     * Returns the identifier of the author
     * @return id
     */
    public int getId() {
        return this.identifier;
    }

    /**
     * Returns the name of the author
     * @return name
     */
    public String getName() {
        return this.author;
    }

    /**
     * Assigns the next available identifier for the author
     * @return identifier
     */
    public int register() {
        this.identifier = Author.nextIdentifier;
        Author.nextIdentifier++;

        return this.identifier;
    }

    /**
     * Prints author information
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + "  " + this.author);
    }
}
