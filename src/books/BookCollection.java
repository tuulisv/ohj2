package books;

import java.util.List;

/**
 * Handles cooperation between classes Books, Authors, and Publishers
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 21.2.2020
 * @version 7.0 20.4.2020
 */
public class BookCollection {

    private Books books;
    private Authors authors;
    private Publishers publishers;

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
    public void add(Book book) {
        this.books.add(book);
    }

    /**
     * Adds an author to the author list
     * @param author added author
     */
    public void add(Author author) {
        this.authors.add(author);
    }

    /**
     * Adds a publisher to the publisher list
     * @param publisher added publisher
     */
    public void add(Publisher publisher) {
        this.publishers.add(publisher);
    }

    /**
     * Replaces a book or adds a new book if it's not in the collection
     * @param book new book
     */
    public void replaceOrAdd(Book book) {
        this.books.replaceOrAdd(book);
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
     * Get the publisher with the given id
     * @param id publisher id
     * @return publisher
     */
    public Publisher getPublisherById(int id) {
        return this.publishers.getPublisherById(id);
    }

    /**
     * Returns the list of authors
     * @return authors in a list
     */
    public List<Author> getAuthors() {
        return this.authors.getAuthors();
    }

    /**
     * Returns the list of publishers
     * @return publishers in a list
     */
    public List<Publisher> getPublishers() {
        return this.publishers.getPublishers();
    }

    /**
     * Returns the list of the author's books
     * @param id author id
     * @return list of the author's books
     */
    public List<Book> getAuthorsWorks(int id) {
        return this.books.getAuthorsWorks(id);
    }

    /**
     * Returns the list of the publisher's books
     * @param id publisher id
     * @return list of publisher's books
     */
    private List<Book> getPublishersBooks(int id) {
        return this.books.getPublishersBooks(id);
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

    /**
     * Get the index of the author corresponding to the id
     * @param id author id
     * @return index of the author
     */
    public int getAuthorIndex(int id) {
        return this.authors.getIndex(id);
    }

    /**
     * Get the index of the book in the author's list of books
     * @param book book
     * @return index of the book
     */
    public int getBookIndex(Book book) {
        int id = book.getId();
        List<Book> works = getAuthorsWorks(book.getAuthorId());
        for (int i = 0; i < works.size(); i++) {
            if (works.get(i).getId() == id) return i;
        }

        return -1;
    }

    /**
     * Sets new file names
     * @param booksFile file name for books
     * @param authFile file name for authors
     * @param pubFile file name for publishers
     */
    public void setFileNames(String booksFile, String authFile, String pubFile) {
        this.books.setFile(booksFile);
        this.authors.setFile(authFile);
        this.publishers.setFile(pubFile);
    }

    /**
     * Finds authors matching to the search term
     * @param str search term
     * @return matching authors
     */
    public List<Author> search(String str) {
        return this.authors.search(str);
    }

    /**
     * Remove book and its author and publisher if they have no other books;
     * last book in the collection is moved to the place of the removed book
     * @param book removed book
     */
    public void remove(Book book) {
        Author author = getAuthorById(book.getAuthorId());
        Publisher publisher = getPublisherById(book.getPubId());
        this.books.remove(book);
        if (getAuthorsWorks(author.getId()).size() == 0) this.authors.remove(author);
        if (getPublishersBooks(publisher.getId()).size() == 0) this.publishers.remove(publisher);
    }

    /**
     * Save changes to all files
     * @throws StoreException if saving fails
     */
    public void save() throws StoreException {
        String error = "";
        try {
            this.books.save();
        } catch (StoreException e) {
            error += e.getMessage();
        }

        try {
            this.authors.save();
        } catch (StoreException e) {
            error += e.getMessage();
        }

        try {
            this.publishers.save();
        } catch (StoreException e) {
            error += e.getMessage();
        }

        if (error.length() > 0) throw new StoreException(error);
    }

    /**
     * Reads all files
     * @throws StoreException if reading fails
     */
    public void readFile() throws StoreException {
        books.readFile();
        authors.readFile();
        publishers.readFile();
    }
}
