package books;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Handles having several books in the program; can add, edit, and remove books.
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 21.2.2020
 * @version 7.0 20.4.2020
 */
public class Books {

    private int no;
    private File file;
    private Book[] books;
    private boolean changed;

    private static int MAX_BOOKS = 10;

    /**
     * Default constructor
     */
    public Books() {
        this.no = 0;
        this.file = new File("books.dat");
        this.books = new Book[MAX_BOOKS];
        this.changed = false;
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
        return this.books[i];
    }

    /**
     * Add a book to the data structure
     * @param book added book
     */
    public void add(Book book) {
        if (this.no >= this.books.length) {
            updateBookArray();
        }

        this.books[no] = book;
        this.no++;
        this.changed = true;
    }

    /**
     * Grows the size of the book array if it's full
     */
    private void updateBookArray() {
        MAX_BOOKS += 10;
        Book[] updatedArray = new Book[MAX_BOOKS];
        for (int i = 0; i < this.books.length; i++) {
            updatedArray[i] = this.books[i];
        }

        this.books = updatedArray;
        this.changed = true;
    }

    /**
     * Replaces a book or adds a new book if it's not in the collection
     * @param book new book
     */
    public void replaceOrAdd(Book book) {
        int id = book.getId();
        for (int i = 0; i < getNoOfBooks(); i++) {
            if (this.books[i].getId() == id) {
                this.books[i] = book;
                this.changed = true;
                return;
            }
        }

        add(book);
    }

    /**
     * Removes book; last book in the collection is moved to the place of the removed book
     * @param book removed book
     */
    protected void remove(Book book) {
        int index = getIndex(book.getId());
        this.books[index] = this.books[no - 1];
        this.books[no - 1] = null;
        this.no--;
        this.changed = true;
    }

    /**
     * Returns the index of the book
     * @param id book id
     * @return index of the book or -1 if not found
     */
    public int getIndex(int id) {
        for (int i = 0; i < getNoOfBooks(); i++) {
            if (getBookByIndex(i).getId() == id) return i;
        }

        return -1;
    }

    /**
     * Returns a list of the author's books in alphabetical order
     * @param id author id
     * @return list of the author's books
     */
    public List<Book> getAuthorsWorks(int id) {
        List<Book> works = new ArrayList<>();
        for (int i = 0; i < getNoOfBooks(); i++) {
            Book book = getBookByIndex(i);
            if (book.getAuthorId() == id) works.add(book);
        }

        Collections.sort(works);
        return works;
    }

    /**
     * Returns a list of the publisher's books
     * @param id publisher id
     * @return list of publisher's books
     */
    public List<Book> getPublishersBooks(int id) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < getNoOfBooks(); i++) {
            Book book = getBookByIndex(i);
            if (book.getPubId() == id) books.add(book);
        }

        return books;
    }

    /**
     * Sets a new file name
     * @param fileName file name
     */
    protected void setFile(String fileName) {
        if (fileName.isEmpty()) return;
        this.file = new File(fileName);
    }

    /**
     * Saves changes to books file
     * @throws StoreException if problems in writing in the file
     */
    protected void save() throws StoreException {
        if (!this.changed) return;
        try (PrintStream ps = new PrintStream(new FileOutputStream(file))) {
            ps.println(";id|title|original title|author|publication year|publisher|language|status|rating");
            for (int i = 0; i < getNoOfBooks(); i++) {
                Book book = getBookByIndex(i);
                ps.println(book.toString());
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        }

        this.changed = false;
    }

    /**
     * Read the books file
     * @throws StoreException if failed to open file
     */
    protected void readFile() throws StoreException {
        try (Scanner s = new Scanner(new FileInputStream(file))) {
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.length() == 0 || line.charAt(0) == ';') continue;
                Book book = new Book();
                book.parse(line);
                add(book);
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        }
    }
}
