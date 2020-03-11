package books;

import java.io.PrintStream;
import java.util.Random;

/**
 * Class for creating Book objects that have a title, author, publication year
 * and other information about the book.
 * @author Tuuli Veini
 * @version 1.0 21.2.2020
 */
public class Book {

    private int identifier;
    private String title;
    private String originalTitle;
    private int authorId;
    private int pubYear;
    private int pubId;
    private String language;
    private boolean status;
    private int rating;

    private static int nextIdentifier = 1;

    /**
     * Default constructor
     */
    public Book() {
        this.title = "";
        this.originalTitle = "";
        this.authorId = -1;
        this.pubYear = 0;
        this.pubId = -1;
        this.language = "";
        this.status = false;
        this.rating = 0;
    }

    /**
     * Constructor that sets the author id
     * @param authorId author id
     * @param pubId publisher id
     */
    public Book(int authorId, int pubId) {
        this.authorId = authorId;
        this.pubId = pubId;
    }

    /**
     * Sets example values for the book
     */
    public void exampleBook() {
        this.title = "The Lord of the Rings " + this.identifier;
        this.originalTitle = "The Lord of the Rings";
        this.pubYear = 1954;
        this.language = "English";
        this.status = true;
        Random r = new Random();
        this.rating = r.nextInt(6);
    }

    /**
     * Assigns the next available identifier to the book
     */
    public void register() {
        this.identifier = Book.nextIdentifier;
        Book.nextIdentifier++;
    }

    /**
     * Returns the identifier of the book
     * @return identifier
     */
    public int getId() {
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
    public int getAuthorId() {
        return this.authorId;
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
    public int getPubId() {
        return this.pubId;
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
     * Prints information about the book
     * @param out output stream
     */
    public void print(PrintStream out) {
        out.println(this.identifier + " " + this.title + " (" + this.originalTitle + ")");
        out.println("  author: " + this.authorId);
        out.println("  published by: " + this.pubId + ", " + this.pubYear);
        out.println("  read: " + this.status);
        out.println("  rating: " + this.rating);
    }
}
