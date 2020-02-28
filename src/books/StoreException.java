package books;

/**
 * Exception class for exceptions in the data structure
 * @author Tuuli Veini
 * @version 21.2.2020
 */
public class StoreException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the exception
     * @param msg exception message
     */
    public StoreException(String msg) {
        super(msg);
    }
}
