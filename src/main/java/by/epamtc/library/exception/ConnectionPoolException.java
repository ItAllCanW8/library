package by.epamtc.library.exception;

/**
 * An exception that provides information on errors thrown by a ConnectionPool.
 *
 * @author Artur Mironchik
 */
public class ConnectionPoolException extends Exception{
    public ConnectionPoolException() {
        super();
    }
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
    public ConnectionPoolException(String message) {
        super(message);
    }
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}

