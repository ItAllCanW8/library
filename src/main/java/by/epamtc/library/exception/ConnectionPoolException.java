package by.epamtc.library.exception;

/**
 * An exception that provides information on errors thrown by a ConnectionPool.
 *
 * @author Artur Mironchik
 */
public class ConnectionPoolException extends Exception{
    /**
     * Instantiates a new Connection pool exception.
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param cause the cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}

