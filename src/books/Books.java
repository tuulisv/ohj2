package books;

/**
 * Handles having several books in the program; can add, edit, and remove books.
 *
 * @author Tuuli Veini
 * @version 1.0 21.2.2020
 */
public class Books {

    private int no;
    private String fileName;
    private Book[] books;

    private static final int MAX_BOOKS = 10;

    /**
     * Default constructor
     */
    public Books() {
        this.no = 0;
        this.fileName = "";
        this.books = new Book[MAX_BOOKS];
    }

    /**
     * Get the number of books
     * @return number of books
     */
    public int getNoOfBooks() {
        return this.no;
    }

    /**
     * Returns the book with the index i
     * @param i index of the book
     * @return book with the corresponding index
     */
    public Book getBookByIndex(int i) {
        return books[i];
    }

    /**
     * Add a book to the data structure
     * @param book added book
     * @throws StoreException if too many items
     */
    public void addBook(Book book) throws StoreException {
        if (this.no >= books.length) {
            throw new StoreException("Too many items");
        }

        this.books[no] = book;
        this.no++;
    }
}
