package books;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles having several publishers in the program
 *
 * @author Tuuli Veini
 * @version 1.0 11.3.2020
 */
public class Publishers {

    private List<Publisher> publishers;
    private File file;
    private boolean changed;

    /**
     * Default constructor
     */
    public Publishers() {
        this.publishers = new ArrayList<>();
        this.file = new File("publishers.dat");
        this.changed = false;
    }

    /**
     * Adds a new publisher
     * @param publisher added publisher
     */
    public void add(Publisher publisher) {
        this.publishers.add(publisher);
        this.changed = true;
    }

    /**
     * Returns the number of publishers
     * @return number of publishers
     */
    public int getNoOfPublishers() {
        return this.publishers.size();
    }

    /**
     * Returns the publisher corresponding to the id
     * @param id id of the publisher
     * @return publisher
     */
    public Publisher getPublisherById(int id) {
        for (Publisher pub: this.publishers) {
            if (pub.getId() == id) {
                return pub;
            }
        }

        return new Publisher();
    }

    /**
     * Returns the publisher at the given index
     * @param index index of the publisher
     * @return publisher at index
     */
    public Publisher getPublisherByIndex(int index) {
        for (int i = 0; i < getNoOfPublishers(); i++) {
            if (i == index) return this.publishers.get(i);
        }

        return new Publisher();
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
     * Saves changes to publishers file
     * @throws StoreException if problems in writing in the file
     */
    public void save() throws StoreException {
        if (!this.changed) return;
        try (PrintStream ps = new PrintStream(new FileOutputStream(file))) {
            ps.println(";pubid|publisher");
            for (int i = 0; i < getNoOfPublishers(); i++) {
                Publisher pub = getPublisherByIndex(i);
                ps.println(pub.toString());
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        } /*catch (IOException e) {
            throw new StoreException("Failed to write in file " + file.getName());
        }*/

        this.changed = false;
    }

    /**
     * Read the publishers file
     * @throws StoreException if failed to open file
     */
    public void readFile() throws StoreException {
        try (Scanner s = new Scanner(new FileInputStream(file))) {
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.length() == 0 || line.charAt(0) == ';') continue;
                Publisher pub = new Publisher();
                pub.parse(line);
                add(pub);
            }
        } catch (FileNotFoundException e) {
            throw new StoreException("Can't open file " + file.getName());
        } /*catch (IOException e) {
            throw new StoreException("Failed to read " + file.getName());
        }*/
    }
}
