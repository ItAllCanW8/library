package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.FileHandler;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Command that uploads user photo.
 *
 * @author Artur Mironchik
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadPhoto implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);

        if (ServletFileUpload.isMultipartContent(req) && user != null) {
            Part part;
            try {
                part = req.getPart(RequestParameter.FILE);
            } catch (IOException | ServletException e) {
                throw new CommandException(e);
            }

            String path = part.getSubmittedFileName();
            if (path != null && !path.isEmpty()) {
                String randomFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(FileHandler.DOT_SYMBOL));
                try (InputStream inputStream = part.getInputStream()) {
                    if (FileHandler.uploadFile(inputStream, FileHandler.WEBAPP_FOLDER_PATH
                                    + FileHandler.PROFILE_PHOTOS_SUBFOLDER + randomFilename)) {
                        UserService service = ServiceFactory.getInstance().getUserService();

                        if (service.changePhoto(user.getUserDetails().getId(), randomFilename)) {
                            user.getUserDetails().setPhotoPath(randomFilename);
                            session.setAttribute(SessionAttribute.USER, user);
                        }
                    }
                } catch (IOException | ServletException | ServiceException e) {
                    req.setAttribute(JspAttribute.ERROR_MSG, e.getMessage());
                    throw new CommandException(e);
                }
            }
        }
        return new CommandResult(req.getContextPath() + CommandName.USER_PROFILE, CommandResult.Type.REDIRECT);
    }
}
