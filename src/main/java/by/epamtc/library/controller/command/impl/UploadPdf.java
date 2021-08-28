package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.FileHandler;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Command that uploads book pdf.
 *
 * @author Artur Mironchik
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadPdf implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String bookId = req.getParameter(RequestParameter.BOOK_ID);

        if (ServletFileUpload.isMultipartContent(req)) {
            Part part = null;
            try {
                part = req.getPart(RequestParameter.BOOK_PDF);
            } catch (IOException | ServletException e) {
                throw new CommandException(e);
            }

            String path = part.getSubmittedFileName();
            if (path != null && !path.isEmpty()) {
                String randomFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(FileHandler.DOT_SYMBOL));
                try (InputStream inputStream = part.getInputStream()) {
                    if (FileHandler.uploadFile(inputStream, FileHandler.WEBAPP_FOLDER_PATH
                            + FileHandler.BOOK_PDF_SUBFOLDER + randomFilename)){
                        BookService service = ServiceFactory.getInstance().getBookService();

                        service.changePdf(Long.parseLong(bookId), randomFilename);
                    }
                } catch (IOException | ServletException | ServiceException e ) {
                    req.setAttribute(JspAttribute.ERROR_MSG, e.getMessage());
                    throw new CommandException(e);
                }
            }
        }
        return new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.REDIRECT);
    }
}
