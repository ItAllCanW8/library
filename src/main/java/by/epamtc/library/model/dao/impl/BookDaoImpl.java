package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.util.SortingHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String bookIdCol = "book_id";
    private static final String bookTitleCol = "title";
    private static final String bookAuthorCol = "author_pseudo";
    private static final String bookIsbnCol = "isbn";
    private static final String bookGenreCol = "genre";
    private static final String bookDescCol = "short_description";
    private static final String bookPdfCol = "pdf";
    private static final String bookImgCol = "img";
    private static final String bookAuthorImgCol = "author_img";
    private static final String bookQuantityCol = "available_quantity";

    public BookDaoImpl() {
    }

    @Override
    public boolean add(Book book) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthorPseudo());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getAvailableQuantity());
            statement.setString(5, book.getGenre());
            statement.setString(6, book.getShortDescription());
            statement.setString(7, book.getPdf());
            statement.setString(8, book.getImg());
            statement.setString(9, book.getAuthorImg());

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error adding a book.", e);
        }
    }

    @Override
    public List<Book> loadPopularBooks() throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_POPULAR_BOOKS);
            while (resultSet.next()) {
                books.add(new Book(resultSet.getLong(bookIdCol), resultSet.getString(bookTitleCol),
                        resultSet.getString(bookImgCol)));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading popular books.", e);
        }
        return books;
    }

    @Override
    public List<Book> loadBooks() throws DaoException {
        List<Book> books = new ArrayList<>();

        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_BOOKS);

            while (resultSet.next()) {
                books.add(createBookFromResultSet(resultSet, false));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading books.", e);
        }

        return books;
    }

    @Override
    public List<Book> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SORT_BOOKS + sortingColumn.getValue() + " "
                    + sortingOrderType.getValue());

            while (resultSet.next()) {
                books.add(createBookFromResultSet(resultSet, false));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error sorting books by " + sortingColumn + " " + sortingOrderType, e);
        }
        return books;
    }

    @Override
    public Optional<Book> findBookById(long bookId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_ID)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            return (resultSet.next() ? Optional.of(createBookFromResultSet(resultSet, true)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book by id.", e);
        }
    }

    @Override
    public Optional<String> findBookCoverById(long bookId) throws DaoException {
        try(Connection connection = pool.takeConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_COVER_BY_ID)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? Optional.of(resultSet.getString(bookImgCol)) : Optional.empty();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book cover by id " + bookId, e);
        }
    }

    @Override
    public Optional<String> findBookPdfById(long bookId) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_PDF_BY_ID)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? Optional.of(resultSet.getString(bookPdfCol)) : Optional.empty();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book pdf by id " + bookId, e);
        }
    }

    @Override
    public List<Book> findBooksByKeyword(String keyword) throws DaoException {
        List<Book> books = new ArrayList<>();

        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOKS_BY_KEYWORD)) {
            String keywordWithWildcard = "%"+keyword+"%";
            statement.setString(1, keywordWithWildcard);
            statement.setString(2, keywordWithWildcard);
            statement.setString(3, keywordWithWildcard);
            statement.setString(4, keywordWithWildcard);
            statement.setString(5, keywordWithWildcard);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                books.add(createBookFromResultSet(resultSet, false));

            return books;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding books by keyword " + keyword, e);
        }
    }

    @Override
    public List<Book> findBooksByGenre(String genre) throws DaoException {
        List<Book> books = new ArrayList<>();

        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOKS_BY_GENRE)) {
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                books.add(createBookFromResultSet(resultSet, false));

            return books;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding books by genre " + genre, e);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws DaoException {
        List<Book> books = new ArrayList<>();

        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOKS_BY_AUTHOR)) {
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                books.add(createBookFromResultSet(resultSet, false));

            return books;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding books by author " + author, e);
        }
    }

    @Override
    public int findBookQuantityById(long bookId) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_QUANTITY_BY_ID)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getShort(bookQuantityCol) : -1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book quantity by id " + bookId, e);
        }
    }

    @Override
    public boolean changeCover(long bookId, String path) throws DaoException {
        return changePhoto(bookId, path, SqlQuery.UPDATE_BOOK_COVER);
    }

    @Override
    public boolean changeAuthorPhoto(long bookId, String path) throws DaoException {
        return changePhoto(bookId, path, SqlQuery.UPDATE_AUTHOR_PHOTO);
    }

    @Override
    public boolean changePdf(long bookId, String path) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK_PDF)) {
            statement.setString(1, path);
            statement.setLong(2, bookId);

            statement.execute();
            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating book's pdf.", e);
        }
    }

    @Override
    public boolean updateBook(Book book) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthorPseudo());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getAvailableQuantity());
            statement.setString(5, book.getGenre());
            statement.setString(6, book.getShortDescription());
            statement.setLong(7, book.getId());

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating book.", e);
        }
    }

    @Override
    public boolean deleteBook(long bookId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BOOK)) {
            statement.setLong(1, bookId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error deleting book.", e);
        }
    }


    @Override
    public boolean isTitleAvailable(String title) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_BOOK_FOR_EXISTENCE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error checking if book title is available.", e);
        }
    }

    @Override
    public boolean updateAvailableQuantity(long bookId, short newQuantity) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK_QUANTITY)) {
            statement.setString(1, String.valueOf(newQuantity));
            statement.setLong(2, bookId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating book's available quantity.", e);
        }
    }

    private boolean changePhoto(long bookId, String path, String query) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, path);
            statement.setLong(2, bookId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating book's photo", e);
        }
    }

    private Book createBookFromResultSet(ResultSet resultSet, boolean areDescAndPdfPresent) throws SQLException {
        long id = resultSet.getLong(bookIdCol);
        String title = resultSet.getString(bookTitleCol);
        String author_pseudo = resultSet.getString(bookAuthorCol);
        String isbn = resultSet.getString(bookIsbnCol);
        String genre = resultSet.getString(bookGenreCol);
        String img = resultSet.getString(bookImgCol);
        String authorImg = resultSet.getString(bookAuthorImgCol);
        short availableQuantity = resultSet.getShort(bookQuantityCol);

        Book book = new Book(id, title, author_pseudo, isbn, availableQuantity, genre, img, authorImg);

        if(areDescAndPdfPresent){
            book.setPdf(resultSet.getString(bookPdfCol));
            book.setShortDescription(resultSet.getString(bookDescCol));
        }

        return book;
    }
}
