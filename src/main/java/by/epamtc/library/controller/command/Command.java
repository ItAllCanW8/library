package by.epamtc.library.controller.command;

import by.epamtc.library.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
