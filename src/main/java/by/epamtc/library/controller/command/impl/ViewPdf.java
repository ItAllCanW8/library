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
 * Command that loads book pdf.
 *
 * @author Artur Mironchik
 */
public class ViewPdf implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String pdf = req.getParameter(RequestParameter.BOOK_PDF);

        if (pdf != null && !pdf.isEmpty()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                outputStream.write(FileHandler.readFile(pdf, FileHandler.BOOK_PDF_SUBFOLDER));
            } catch (IOException | ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
        return null;
    }
}
