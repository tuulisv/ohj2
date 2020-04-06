package books;

import fi.jyu.mit.ohj2.Mjonot;

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
     * Sets the author id and grows next id if necessary
     * @param id set id
     */
    private void setIdentifier(int id) {
        this.identifier = id;
        if (this.identifier >= Author.nextIdentifier) Author.nextIdentifier++;
    }

    /**
     * Resets the id to 1
     */
    public static void clearIdentifier(){
        Author.nextIdentifier = 1;
    }

    /**
     * Parse author information from the line
     * @param line
     */
    public void parse(String line) {
        StringBuilder sb = new StringBuilder(line);
        setIdentifier(Mjonot.erota(sb, '|', this.identifier));
        this.author = Mjonot.erota(sb, '|', this.author);
    }

    /**
     * Prints author information
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + "  " + this.author);
    }

    /**
     * Returns author in String format
     * @return author as String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier + "|");
        sb.append(this.author);
        return sb.toString();
    }
}
