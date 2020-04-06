package books;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles having several authors in the program
 *
 * @author Tuuli Veini
 * @version 1.0 26.2.2020
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
        this.changed = true;
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
     * Sets a new file name
     * @param fileName file name
     */
    public void setFile(String fileName) {
        if (fileName.isEmpty()) return;
        this.file = new File(fileName);
    }

    /**
     * Saves changes to authors file
     * @throws StoreException if problems in writing in the file
     */
    public void save() throws StoreException {
        if (!this.changed) return;
        try (PrintStream ps = new PrintStream(new FileOutputStream(file))) {
            ps.println(";authid|author");
            for (int i = 0; i < getNoOfAuthors(); i++) {
                Author author = getAuthorByIndex(i);
                ps.println(author.toString());
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        } /*catch (IOException e) {
            throw new StoreException("Failed to write in file " + file.getName());
        }*/

        this.changed = false;
    }

    /**
     * Read the authors file
     * @throws StoreException if failed to open file
     */
    public void readFile() throws StoreException {
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
        } /*catch (IOException e) {
            throw new StoreException("Failed to read " + file.getName());
        }*/
    }
}