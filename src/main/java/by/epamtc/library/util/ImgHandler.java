package by.epamtc.library.util;

import by.epamtc.library.exception.ServiceException;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgHandler {
    public static final String DOT_SYMBOL = ".";
    private static final String DEFAULT_AVATAR = "default_avatar.png";
    public static final String IMG_FOLDER_PATH = "F:" + File.separator + "University" + File.separator
            + "EPAM" + File.separator + "library" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "images" + File.separator;
    public static final String PROFILE_PHOTOS_SUBFOLDER = "profile-photos" + File.separator;
    public static final String BOOK_COVERS_SUBFOLDER = "book-covers" + File.separator;

    public static boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            if (inputStream.read(bytes) != -1) {
                FileOutputStream fops = new FileOutputStream(path);
                fops.write(bytes);
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }

    public static byte[] readFile(String fileName, String subfolder) throws ServiceException {
        byte[] result;
        String fileUri = ImgHandler.IMG_FOLDER_PATH + subfolder + fileName;
        Path filePath = Paths.get(fileUri);
        if (!Files.exists(filePath)) {
            filePath = Paths.get(ImgHandler.IMG_FOLDER_PATH + subfolder + DEFAULT_AVATAR);
        }
        try {
            result = Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    private ImgHandler(){}
}
