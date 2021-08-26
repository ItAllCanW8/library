package by.epamtc.library.exception;

/**
 * An exception that provides information on errors thrown by Dao objects.
 *
 * @author Artur Mironchik
 */
public class DaoException extends Exception{
    public DaoException() {
        super();
    }
    public DaoException(Throwable cause) {
        super(cause);
    }
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
