package by.epamtc.library.exception;

/**
 * An exception that provides information on errors thrown by Service objects.
 *
 * @author Artur Mironchik
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
