package by.epamtc.library.util;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.factory.impl.BookFactory;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    public static final String DOT_SYMBOL = ".";
    private static final String DEFAULT_AVATAR = "default_avatar.png";
    public static final String WEBAPP_FOLDER_PATH = "F:" + File.separator + "University" + File.separator
            + "EPAM" + File.separator + "library" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator;
//    public static final String IMG_FOLDER_PATH = "F:" + File.separator + "University" + File.separator
//            + "EPAM" + File.separator + "library" + File.separator + "src" + File.separator + "main" + File.separator +
//            "webapp" + File.separator + "images" + File.separator;

    public static final String PROFILE_PHOTOS_SUBFOLDER = "images" + File.separator + "profile-photos" + File.separator;
    public static final String BOOK_COVERS_SUBFOLDER = "images" + File.separator + "book-covers" + File.separator;
    public static final String BOOK_PDF_SUBFOLDER = "pdf" + File.separator;

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
        String fileUri = FileHandler.WEBAPP_FOLDER_PATH + subfolder + fileName;
        Path filePath = Paths.get(fileUri);
        if (!Files.exists(filePath)) {
            filePath = Paths.get(FileHandler.WEBAPP_FOLDER_PATH + subfolder + DEFAULT_AVATAR);
        }
        try {
            result = Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public static void deleteBookFiles(String bookCoverName, String authorPhotoName){
        if(!bookCoverName.equals(BookFactory.DEFAULT_COVER)) {
            File bookCover = new File(WEBAPP_FOLDER_PATH + BOOK_COVERS_SUBFOLDER, bookCoverName);
            System.out.println(bookCover);
//            bookCover.delete();
        }

        if(!authorPhotoName.equals(BookFactory.DEFAULT_AUTHOR_PHOTO)) {
            File authorPhoto = new File(WEBAPP_FOLDER_PATH + BOOK_COVERS_SUBFOLDER, authorPhotoName);
            System.out.println(authorPhoto);
//            authorPhoto.delete();
        }
    }

    private FileHandler(){}
}
