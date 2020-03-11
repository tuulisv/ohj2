package books;

import java.util.List;
import java.util.Random;

/**
 * Handles cooperation between classes Books, Authors, and Publishers
 *
 * @author Tuuli Veini
 * @version 1.0 21.2.2020
 */
public class BookCollection {

    Books books;
    Authors authors;
    Publishers publishers;

    /**
     * Default constructor
     */
    public BookCollection() {
        this.books = new Books();
        this.authors = new Authors();
        this.publishers = new Publishers();
    }

    /**
     * Adds a book to the collection
     * @param book added book
     */
    public void addBook(Book book) {
        this.books.addBook(book);
    }

    /**
     * Adds an author to the author list
     * @param author added author
     */
    public void addAuthor(Author author) {
        this.authors.addAuthor(author);
    }

    /**
     * Adds a publisher to the publisher list
     * @param publisher added publisher
     */
    public void addPublisher(Publisher publisher) {
        this.publishers.addPublisher(publisher);
    }

    /**
     * Get the book at index i
     * @param i index of the book
     * @return book at index i
     */
    public Book getBook(int i) {
        return this.books.getBookByIndex(i);
    }

    /**
     * Get the author at index i
     * @param i index of the author
     * @return author at index i
     */
    public Author getAuthor(int i) {
        return this.authors.getAuthorByIndex(i);
    }

    /**
     * Get the author corresponding to the id
     * @param id author id
     * @return author with the given id
     */
    public Author getAuthorById(int id) {
        return this.authors.getAuthorById(id);
    }

    /**
     * Get the publisher at index i
     * @param i index of the publisher
     * @return publisher at index i
     */
    public Publisher getPublisher(int i) {
        return this.publishers.getPublisherByIndex(i);
    }

    /**
     * Get a random publisher
     * @return random publisher
     */
    public Publisher getRandomPublisher() {
        Random r = new Random();
        int i = r.nextInt(getNoOfPublishers());
        return this.publishers.getPublisherByIndex(i);
    }

    /**
     * Returns the list of the author's books
     * @param author author
     * @return list of the author's books
     */
    public List<Book> getAuthorsWorks(Author author) {
        return this.books.getAuthorsWorks(author);
    }

    /**
     * Get the number of books in the collection
     * @return number of books
     */
    public int getNoOfBooks() {
        return this.books.getNoOfBooks();
    }

    /**
     * Get the number of authors in the collection
     * @return number of authors
     */
    public int getNoOfAuthors() {
        return this.authors.getNoOfAuthors();
    }

    /**
     * Get the number of publishers in the collection
     * @return number of publishers
     */
    public int getNoOfPublishers() {
        return this.publishers.getNoOfPublishers();
    }
}
