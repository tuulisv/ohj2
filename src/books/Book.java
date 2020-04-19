package books;

import fi.jyu.mit.ohj2.Mjonot;
import java.io.PrintStream;

/**
 * Class for creating Book objects that have a title, author, publication year
 * and other information about the book.
 * @author Tuuli Veini
 * @version 1.0 21.2.2020
 */
public class Book implements Comparable<Book> {

    private int identifier;
    private String title;
    private String originalTitle;
    private int authorId;
    private int pubYear;
    private int pubId;
    private String language;
    private int status;
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
        this.status = 0;
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
    public int getStatus() {
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
     * Sets new title
     * @param title book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets new original title
     * @param ogTitle original title
     */
    public void setOriginalTitle(String ogTitle) {
        this.originalTitle = ogTitle;
    }

    /**
     * Sets new author id
     * @param id author id
     */
    public void setAuthorId(int id) {
        this.authorId = id;
    }

    /**
     * Sets new publiscation year
     * @param year publication year
     */
    public void setPubYear(int year) {
        this.pubYear = year;
    }

    /**
     * Sets new publisher id
     * @param id publisher id
     */
    public void setPubId(int id) {
        this.pubId = id;
    }

    /**
     * Sets new language
     * @param lang book language
     */
    public void setLanguage(String lang) {
        this.language = lang;
    }

    /**
     * Sets reading status
     * @param status reading status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Sets rating
     * @param rating rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Sets the book id and grows nextIdentifier if necessary
     * @param id set id
     */
    private void setIdentifier(int id) {
        this.identifier = id;
        if (this.identifier >= Book.nextIdentifier) Book.nextIdentifier = this.identifier + 1;
    }

    /**
     * Resets the id to 1
     */
    public static void clearIdentifier() {
        Book.nextIdentifier = 1;
    }

    /**
     * Parse book information from the line
     * @param line String containing book information
     */
    public void parse(String line) {
        StringBuilder sb = new StringBuilder(line);
        setIdentifier(Mjonot.erota(sb, '|', this.identifier));
        this.title = Mjonot.erota(sb, '|', this.title);
        this.originalTitle = Mjonot.erota(sb, '|', this.originalTitle);
        this.authorId = Mjonot.erota(sb, '|', this.authorId);
        this.pubYear = Mjonot.erota(sb, '|', this.pubYear);
        this.pubId = Mjonot.erota(sb, '|', this.pubId);
        this.language = Mjonot.erota(sb, '|', this.language);
        this.status = (Mjonot.erota(sb, '|', 0) == 1) ? 1 : 0;
        this.rating = Mjonot.erota(sb, '|', this.rating);
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

    /**
     * Returns book in String format
     * @return book as String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier).append("|");
        sb.append(this.title).append("|");
        sb.append(this.originalTitle).append("|");
        sb.append(this.authorId).append("|");
        sb.append(this.pubYear).append("|");
        sb.append(this.pubId).append("|");
        sb.append(this.language).append("|");
        sb.append(this.status).append("|");
        sb.append(this.rating);
        return sb.toString();
    }

    /**
     * Compare books based on their publication years
     * @param o compared book
     * @return comparison result
     */
    @Override
    public int compareTo(Book o) {
        return this.pubYear - o.getPubYear();
    }
}
