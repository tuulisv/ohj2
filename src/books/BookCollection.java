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
     * Get the book at index i
     * @param i index of the book
     * @return book at index i
     */
    public Book getBook(int i) {
        return this.books.getBookByIndex(i);
    }

    /**
     * Get the number of books in the collection
     * @return number of books
     */
    public int getNoOfBooks() {
        return books.getNoOfBooks();
    }
}
