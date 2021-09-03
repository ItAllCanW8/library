package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;

import by.epamtc.library.util.FileHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that loads book cover.
 *
 * @author Artur Mironchik
 */
public class LoadBookCover implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String fileName = req.getParameter(RequestParameter.FILE_NAME);

        if (fileName != null && !fileName.isEmpty()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                outputStream.write(FileHandler.readFile(fileName, FileHandler.BOOK_COVERS_SUBFOLDER));
            } catch (IOException | ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
        return null;
    }
}
