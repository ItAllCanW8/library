package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.dao.factory.DaoFactory;
import by.epamtc.library.model.entity.*;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.BookRequestFactory;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.DateTimeHelper;
import by.epamtc.library.util.SortingHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRequestServiceImpl implements BookRequestService {
    private static final BookRequestDao bookRequestDao = DaoFactory.getInstance().getBookRequestDao();
    private static final LibraryFactory<BookRequest> bookRequestFactory = BookRequestFactory.getInstance();

    public BookRequestServiceImpl() {
    }

    @Override
    public boolean bookRequestExists(BookRequest request) throws ServiceException {
        try {
            return bookRequestDao.bookRequestExists(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException {
        Optional<BookRequest> requestOptional = bookRequestFactory.create(fields);
        BookRequestType bookRequestType = BookRequestType.fromString(fields.get(RequestParameter.REQUEST_TYPE));

        if (bookRequestType == null)
            return false;

        boolean isToReadingRoom = bookRequestType.equals(BookRequestType.TO_READING_ROOM);

        try {
            if (requestOptional.isPresent()) {
                BookService bookService = ServiceFactory.getInstance().getBookService();
                long bookId = Long.parseLong(fields.get(RequestParameter.BOOK_ID));
                Optional<Book> bookOptional = bookService.findBookById(bookId);

                if (bookOptional.isPresent()) {
                    BookRequest request = requestOptional.get();
                    Book book = bookOptional.get();

                    if (!isToReadingRoom && book.getAvailableQuantity() <= 0)
                        return false;

                    request.setUser(reader);
                    request.setBook(book);

                    boolean isRequestCreated = !bookRequestDao.bookRequestExists(request) && bookRequestDao.add(request);
                    if (!isToReadingRoom && isRequestCreated) {
                        bookService.updateAvailableQuantity(bookId, (short) (book.getAvailableQuantity() - 1));
                    }

                    return isRequestCreated;
                }
            }
        } catch (NumberFormatException | DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<BookRequest> loadBookRequests() throws ServiceException {
        try {
            return bookRequestDao.loadBookRequests();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeRequestState(long requestId, String newRequestStateStr) throws ServiceException {
        try {
            BookRequestState newRequestState = BookRequestState.fromString(newRequestStateStr);

            if(newRequestState != null) {
                Optional<String> expectedReturnDate = Optional.empty();

                if (newRequestState == BookRequestState.APPROVED) {
                    Optional<String> numberOfDaysCoeffOptional = bookRequestDao.loadNumberOfDaysCoeff();
                    if (numberOfDaysCoeffOptional.isPresent()) {
                        int numberOfDaysCoeff = Integer.parseInt(numberOfDaysCoeffOptional.get());

                        expectedReturnDate = Optional.of(LocalDateTime.now().plusDays(numberOfDaysCoeff).
                                format(DateTimeHelper.formatter));
                    }
                }
                return bookRequestDao.changeRequestState(requestId, newRequestState.getValue(), expectedReturnDate);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean closeBookRequest(long userId, long requestId, long bookId, short bookQuantity, BookRequestType requestType,
        String expectedReturnDateStr) throws ServiceException {
        try {
            boolean isToReadingRoom = requestType.equals(BookRequestType.TO_READING_ROOM);
            boolean isRequestClosed = bookRequestDao.closeBookRequest(requestId);

            if (!isToReadingRoom && isRequestClosed) {
                BookService bookService = ServiceFactory.getInstance().getBookService();
                UserService userService = ServiceFactory.getInstance().getUserService();

                String closingDateStr = LocalDateTime.now().format(DateTimeHelper.formatter);

                LocalDateTime closingDate = LocalDateTime.parse(closingDateStr,DateTimeHelper.formatter);
                LocalDateTime expectedReturnDate = LocalDateTime.parse(expectedReturnDateStr,DateTimeHelper.formatter);

                if(closingDate.isAfter(expectedReturnDate))
                    userService.changeUserStatus(userId, UserStatus.UNRELIABLE.getValue());


                bookService.updateAvailableQuantity(bookId, (short) (bookQuantity + 1));
            }

            return isRequestClosed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookRequest(long requestId) throws ServiceException {
        try {
            return bookRequestDao.deleteBookRequest(requestId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findEmailByRequestId(long requestId) throws ServiceException {
        try {
            Optional<String> emailOptional = bookRequestDao.findEmailByRequestId(requestId);
            if (emailOptional.isPresent()) {
                String email = emailOptional.get();
                emailOptional = Optional.of(email);
            }
            return emailOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException {
        try {
            return bookRequestDao.loadBookRequestsByReaderId(readerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookRequest> loadReadingRoomByReaderId(long readerId) throws ServiceException {
        try {
            return bookRequestDao.loadReadingRoomByReaderId(readerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookRequest> sort(String sortingField, String sortingOrder) throws ServiceException {
        try {
            SortingHelper.SortingColumn sortingColumn = SortingHelper.SortingColumn.fromString(sortingField);
            SortingHelper.SortingOrderType sortingOrderType = SortingHelper.SortingOrderType.fromString(sortingOrder);

            if (sortingColumn != null && sortingOrderType != null)
                return bookRequestDao.sort(sortingColumn, sortingOrderType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return new ArrayList<>(0);
    }

    @Override
    public List<BookRequest> findBookRequestsByType(String type) throws ServiceException {
        try {
            BookRequestType reqType = BookRequestType.fromString(type);

            if (reqType != null)
                return bookRequestDao.findBookRequestsByType(reqType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<BookRequest> findBookRequestsByState(String state) throws ServiceException {
        try {
            BookRequestState requestState = BookRequestState.fromString(state);

            if (requestState != null)
                return bookRequestDao.findBookRequestsByState(requestState);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return new ArrayList<>(0);
    }
}
