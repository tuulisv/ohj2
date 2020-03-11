package books;

import java.util.ArrayList;
import java.util.List;

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

    private static int MAX_BOOKS = 10;

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
     */
    public void addBook(Book book) {
        if (this.no >= books.length) {
            updateBookArray();
        }

        this.books[no] = book;
        this.no++;
    }

    /**
     * Grows the size of the book array if it's full
     */
    public void updateBookArray() {
        MAX_BOOKS += 10;
        Book[] updatedArray = new Book[MAX_BOOKS];
        for (int i = 0; i < books.length; i++) {
            updatedArray[i] = books[i];
        }

        books = updatedArray;
    }

    /**
     * Returns a list of the author's books
     * @param author author
     * @return list of the author's books
     */
    public List<Book> getAuthorsWorks(Author author) {
        List<Book> works = new ArrayList<>();
        for (int i = 0; i < getNoOfBooks(); i++) {
            Book book = getBookByIndex(i);
            if (book.getAuthorId() == author.getId()) works.add(book);
        }

        return works;
    }
}
