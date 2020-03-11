package books;

import java.io.PrintStream;

/**
 * Class for creating an Author object
 * @author Tuuli Veini
 * @version 1.0 26.2.2020
 */
public class Author {

    private int identifier;
    private String author;

    private static int nextIdentifier = 1;

    /**
     * Default constructor
     */
    public Author() {
        this.author = "";
    }

    /**
     * Sets an example value
     */
    public void exampleAuthor() {
        this.author = "J. R. R. Tolkien " + this.identifier;
    }

    /**
     * Assigns the next available identifier for the author
     */
    public void register() {
        this.identifier = Author.nextIdentifier;
        Author.nextIdentifier += 1;
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
     * Resets the id to 1
     */
    public static void clearIdentifier(){
        Author.nextIdentifier = 1;
    }

    /**
     * Prints author information
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + "  " + this.author);
    }
}
