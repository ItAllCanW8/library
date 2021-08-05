package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.BookServiceImpl;
import by.epamtc.library.util.ImgHandler;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadBookCover implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String bookId = req.getParameter(RequestParameter.BOOK_ID);
        BookService service = BookServiceImpl.getInstance();

        try {
            Optional<Book> bookOptional = service.findBookById(Long.parseLong(bookId));
            if (ServletFileUpload.isMultipartContent(req) && bookOptional.isPresent()) {
                Book book = bookOptional.get();
                Part part = null;
                try {
                    part = req.getPart(RequestParameter.BOOK_COVER);
                } catch (IOException | ServletException e) {
                    throw new CommandException(e);
                }

                String path = part.getSubmittedFileName();
                if (path != null && !path.isEmpty()) {
                    String randomFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(ImgHandler.DOT_SYMBOL));
                    try (InputStream inputStream = part.getInputStream()) {
                        if (ImgHandler.uploadFile(inputStream, ImgHandler.IMG_FOLDER_PATH
                                + ImgHandler.BOOK_COVERS_SUBFOLDER + randomFilename)) {
                            if(service.changeCover(book.getId(), randomFilename)){
                                book.setImg(randomFilename);
                                req.setAttribute(RequestParameter.BOOK, book);
                            }
                        }
                    }
                }
            }
        } catch (IOException | ServletException | ServiceException e) {
            req.setAttribute(JspAttribute.ERROR_MSG, e.getMessage());
            throw new CommandException(e);
        }
        return new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.REDIRECT);
    }
}
