package books;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles having several authors in the program
 *
 * @author Tuuli Veini
 * @version 1.0 26.2.2020
 */
public class Authors {

    private List<Author> authors;

    /**
     * Default constructor
     */
    public Authors() {
        this.authors = new ArrayList<>();
    }

    /**
     * Adds a new author
     * @param author added author
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
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
            if (auth.getIdentifier() == id) {
                searchedAuthor = auth;
            }
        }

        return searchedAuthor;
    }
}