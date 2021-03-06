package books;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles having several authors in the program
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 26.2.2020
 * @version 7.0 20.4.2020
 */
public class Authors {

    private List<Author> authors;
    private File file;
    private boolean changed;

    /**
     * Default constructor
     */
    public Authors() {
        this.authors = new ArrayList<>();
        this.file = new File("authors.dat");
        this.changed = false;
    }

    /**
     * Adds a new author
     * @param author added author
     */
    public void add(Author author) {
        this.authors.add(author);
        Collections.sort(this.authors);
        this.changed = true;
    }

    /**
     * Returns the list of authors
     * @return list of authors
     */
    protected List<Author> getAuthors() {
        Collections.sort(this.authors);
        return this.authors;
    }

    /**
     * Returns the number of authors
     * @return number of authors
     */
    public int getNoOfAuthors() {
        return this.authors.size();
    }

    /**
     * Returns the author author corresponding to the id
     * @param id author id
     * @return author
     */
    public Author getAuthorById(int id) {
        Author searchedAuthor = new Author();

        for (Author auth: this.authors) {
            if (auth.getId() == id) {
                searchedAuthor = auth;
            }
        }

        return searchedAuthor;
    }

    /**
     * Returns the author at the given index
     * @param index index of the author
     * @return author
     */
    public Author getAuthorByIndex(int index) {
        for (int i = 0; i < getNoOfAuthors(); i++) {
            if (index == i) return this.authors.get(i);
        }

        return new Author();
    }

    /**
     * Returns the index of the author
     * @param id author id
     * @return index of the author or -1 if not found
     */
    public int getIndex(int id) {
        for (int i = 0; i < getNoOfAuthors(); i++) {
            if (getAuthorByIndex(i).getId() == id) return i;
        }

        return -1;
    }

    /**
     * Finds authors matching to the search term and returns them as a list
     * @param str search term
     * @return matching authors
     */
    public List<Author> search(String str) {
        List<Author> items = new ArrayList<>();
        String regex = "(?i).*(" + Pattern.quote(str) + ").*";

        for (int i = 0; i < getNoOfAuthors(); i++) {
            Author author = this.authors.get(i);
            if (author.getName().matches(regex)) items.add(author);
        }

        Collections.sort(items);
        return items;
    }

    /**
     * Remove author from the list
     * @param author removed author
     */
    public void remove(Author author) {
        this.authors.remove(author);
        this.changed = true;
    }

    /**
     * Sets a new file name
     * @param fileName file name
     */
    protected void setFile(String fileName) {
        if (fileName.trim().isEmpty()) return;
        this.file = new File(fileName);
    }

    /**
     * Saves changes to authors file
     * @throws StoreException if problems in writing in the file
     */
    protected void save() throws StoreException {
        if (!this.changed) return;
        try (PrintStream ps = new PrintStream(new FileOutputStream(file))) {
            ps.println(";authid|author");
            for (int i = 0; i < getNoOfAuthors(); i++) {
                Author author = getAuthorByIndex(i);
                ps.println(author.print());
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        }

        this.changed = false;
    }

    /**
     * Read the authors file
     * @throws StoreException if failed to open file
     */
    protected void readFile() throws StoreException {
        try (Scanner s = new Scanner(new FileInputStream(file))) {
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.length() == 0 || line.charAt(0) == ';') continue;
                Author author = new Author();
                author.parse(line);
                add(author);
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        }
    }
}