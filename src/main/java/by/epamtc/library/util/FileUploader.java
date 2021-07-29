package by.epamtc.library.util;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploader {
    public static final String DOT_SYMBOL = ".";

    public static final String UPLOAD_PHOTO_PATH = "F:" + File.separator + "University" + File.separator
            + "EPAM" + File.separator + "library" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "images" + File.separator + "profile-photos" + File.separator;

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

    private FileUploader(){}
}
