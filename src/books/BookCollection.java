package books;

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
     * @throws StoreException if too many items
     */
    public void addBook(Book book) throws StoreException {
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
}
