package books;

import java.io.PrintStream;
import java.util.Random;

/**
 * Class for creating Book objects that have a title, author, publication year
 * and other information about the book.
 *
 * @author Tuuli Veini
 * @version 1.0 21.2.2020
 */
public class Book {

    private int identifier;
    private String title;
    private String originalTitle;
    private int authorIndex;
    private int pubYear;
    private int pubIndex;
    private String language;
    private boolean status;
    private int rating;

    private static int nextIdentifier;

    /**
     * Default constructor
     */
    public Book() {
        this.identifier = -1;
        this.title = "";
        this.originalTitle = "";
        this.authorIndex = -1;
        this.pubYear = 0;
        this.pubIndex = -1;
        this.language = "";
        this.status = false;
        this.rating = 0;

        nextIdentifier = 1;
    }

    /**
     * Sets example values for the book
     */
    public void exampleBook() {
        this.identifier = 1;
        this.title = "The Lord of the Rings";
        this.originalTitle = "The Lord of the Rings";
        this.authorIndex = 2;
        this.pubYear = 1954;
        this.pubIndex = 2;
        this.language = "English";
        this.status = true;
        Random r = new Random();
        this.rating = r.nextInt(6);

        Book.nextIdentifier = 2;
    }

    /**
     * Returns the identifier of the book
     * @return identifier
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the title of the book
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the original title of the book
     * @return original title
     */
    public String getOrigTitle() {
        return this.originalTitle;
    }

    /**
     * Returns the index of the author
     * @return index of the author
     */
    public int getAuthorIndex() {
        return this.authorIndex;
    }

    /**
     * Returns the publication year
     * @return publication year
     */
    public int getPubYear() {
        return this.pubYear;
    }

    /**
     * Returns the publisher index
     * @return publisher index
     */
    public int getPubIndex() {
        return this.pubIndex;
    }

    /**
     * Returns the language of the book
     * @return language
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Returns the reading status
     * @return status
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Returns the rating
     * @return rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Assigns the next available identifier to the book
     * @return identifier of the book
     */
    public int register() {
        this.identifier = Book.nextIdentifier;
        Book.nextIdentifier++;

        return this.identifier;
    }

    /**
     * Prints information about the book
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + " " + this.title + " (" + this.originalTitle + ")");
        out.println("author: " + this.authorIndex);
        out.println("published by: " + this.pubIndex + ", " + this.pubYear);
        out.println("read: " + this.status);
        out.println("rating: " + this.rating);
    }
}
