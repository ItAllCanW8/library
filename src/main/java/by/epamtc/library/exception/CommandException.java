package by.epamtc.library.exception;

/**
 * An exception that provides information on errors occurred while processing a command.
 *
 * @author Artur Mironchik
 */
public class CommandException extends Exception{
    public CommandException() {
        super();
    }
    public CommandException(Throwable cause) {
        super(cause);
    }
    public CommandException(String message) {
        super(message);
    }
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
