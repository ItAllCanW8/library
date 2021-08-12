package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.BookServiceImpl;
import by.epamtc.library.util.FileHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoadBookCover implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String fileName = null;

        if(req.getParameterMap().containsKey(RequestParameter.BOOK_ID)){
            long bookId = Long.parseLong(req.getParameter(RequestParameter.BOOK_ID));
            BookService service = BookServiceImpl.getInstance();

            try{
                Optional<String> bookCoverOptional = service.findBookCoverById(bookId);

                if(bookCoverOptional.isPresent())
                    fileName = bookCoverOptional.get();

            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }

        } else
            fileName = req.getParameter(RequestParameter.FILE_NAME);


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
