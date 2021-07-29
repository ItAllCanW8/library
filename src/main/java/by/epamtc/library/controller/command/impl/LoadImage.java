package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.util.FileUploader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadImage implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_AVATAR = "default_avatar.png";
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String fileName = req.getParameter(RequestParameter.FILE);
        if (fileName != null && !fileName.isEmpty()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                outputStream.write(readFile(fileName));
            } catch (IOException | ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
        return null;
    }

    private byte[] readFile(String fileName) throws ServiceException {
        byte[] result;
        String fileUri = FileUploader.UPLOAD_PHOTO_PATH + fileName;
        Path filePath = Paths.get(fileUri);
        if (!Files.exists(filePath)) {
            filePath = Paths.get(FileUploader.UPLOAD_PHOTO_PATH + DEFAULT_AVATAR);
        }
        try {
            result = Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
