package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command that librarian management books page.
 *
 * @author Artur Mironchik
 */
public class LibrarianBooksPage implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        LoadBooks.loadBooks(req);
        return new CommandResult(PagePath.LIBRARIAN_BOOKS, CommandResult.Type.FORWARD);
    }
}
