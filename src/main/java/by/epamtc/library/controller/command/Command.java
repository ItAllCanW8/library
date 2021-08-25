package by.epamtc.library.controller.command;

import by.epamtc.library.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that represents "Command" pattern. Used by a controller.
 *
 * @author Artur Mironchik
 */
public interface Command {
    /**
     * Processes a request from controller and returns the page to be redirected.
     *
     * @param req  Request object.
     * @param resp Response object.
     * @return CommandResult object.
     * @throws CommandException if an exception has occurred while executing.
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
