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

/**
 * Class provides methods for loading and uploading files.
 *
 * @author Artur Mironchik
 */
public class FileHandler {
    /**
     * The constant DOT_SYMBOL.
     */
    public static final String DOT_SYMBOL = ".";
    private static final String DEFAULT_AVATAR = "default_avatar.png";
    /**
     * The constant WEBAPP_FOLDER_PATH.
     */
    public static final String WEBAPP_FOLDER_PATH = "F:" + File.separator + "University" + File.separator
            + "EPAM" + File.separator + "library" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator;

    /**
     * The constant PROFILE_PHOTOS_SUBFOLDER.
     */
    public static final String PROFILE_PHOTOS_SUBFOLDER = "images" + File.separator + "profile-photos" + File.separator;
    /**
     * The constant BOOK_COVERS_SUBFOLDER.
     */
    public static final String BOOK_COVERS_SUBFOLDER = "images" + File.separator + "book-covers" + File.separator;
    /**
     * The constant BOOK_PDF_SUBFOLDER.
     */
    public static final String BOOK_PDF_SUBFOLDER = "pdf" + File.separator;

    private FileHandler() {
    }

    /**
     * Upload file boolean.
     *
     * @param inputStream the input stream
     * @param path        the path
     * @return the boolean
     * @throws ServletException the servlet exception
     */
    public static boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            if (inputStream.read(bytes) != -1) {
                FileOutputStream fops = new FileOutputStream(path);
                fops.write(bytes);
                fops.close();
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }

    /**
     * Read file byte [ ].
     *
     * @param fileName  the file name
     * @param subfolder the subfolder
     * @return the byte [ ]
     * @throws ServiceException the service exception
     */
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

    /**
     * Delete book files.
     *
     * @param bookCoverName   the book cover name
     * @param authorPhotoName the author photo name
     * @param pdfName         the pdf name
     */
    public static void deleteBookFiles(String bookCoverName, String authorPhotoName, String pdfName) {
        if (!bookCoverName.equals(BookFactory.DEFAULT_COVER)) {
            File bookCover = new File(WEBAPP_FOLDER_PATH + BOOK_COVERS_SUBFOLDER, bookCoverName);
            bookCover.delete();
        }

        if (!authorPhotoName.equals(BookFactory.DEFAULT_AUTHOR_PHOTO)) {
            File authorPhoto = new File(WEBAPP_FOLDER_PATH + BOOK_COVERS_SUBFOLDER, authorPhotoName);
            authorPhoto.delete();
        }

        if (!authorPhotoName.equals(BookFactory.DEFAULT_PDF)) {
            File pdf = new File(WEBAPP_FOLDER_PATH + BOOK_PDF_SUBFOLDER, pdfName);
            pdf.delete();
        }
    }
}
