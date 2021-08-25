package by.epamtc.library.controller.command;

import by.epamtc.library.controller.command.impl.*;

/**
 * Enumeration of command types.
 *
 * @author Artur Mironchik
 */
public enum CommandType {
    /**
     * Represents home command implementation of Command interface.
     */
    HOME {{
        this.command = new Home();
    }},
    /**
     * Represents register command implementation of Command interface.
     */
    REGISTER {{
        this.command = new Register();
    }},
    /**
     * Represents login command implementation of Command interface.
     */
    LOGIN {{
        this.command = new Login();
    }},
    /**
     * Represents logout command implementation of Command interface.
     */
    LOGOUT {{
        this.command = new Logout();
    }},
    /**
     * Represents change language command implementation of Command interface.
     */
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguage();
    }},
    /**
     * Represents load books command implementation of Command interface.
     */
    LOAD_BOOKS {{
        this.command = new LoadBooks();
    }},
    /**
     * Represents load book info command implementation of Command interface.
     */
    LOAD_BOOK_INFO{{
        this.command = new LoadBookInfo();
    }},
    /**
     * Represents user profile command implementation of Command interface.
     */
    USER_PROFILE{{
        this.command = new UserProfile();
    }},
    /**
     * Represents upload photo command implementation of Command interface.
     */
    UPLOAD_PHOTO{{
        this.command = new UploadPhoto();
    }},
    /**
     * Represents load profile photo command implementation of Command interface.
     */
    LOAD_PROFILE_PHOTO{{
        this.command = new LoadProfilePhoto();
    }},
    /**
     * Represents load book cover command implementation of Command interface.
     */
    LOAD_BOOK_COVER{{
        this.command = new LoadBookCover();
    }},
    /**
     * Represents edit user profile command implementation of Command interface.
     */
    EDIT_USER_PROFILE{{
        this.command = new EditUserProfile();
    }},
    /**
     * Represents change password command implementation of Command interface.
     */
    CHANGE_PASSWORD{{
        this.command = new ChangePassword();
    }},
    /**
     * Represents deactivate account command implementation of Command interface.
     */
    DEACTIVATE_ACCOUNT{{
        this.command = new DeactivateAccount();
    }},
    /**
     * Represents users command implementation of Command interface.
     */
    USERS{{
        this.command = new Users();
    }},
    /**
     * Represents find users by role command implementation of Command interface.
     */
    FIND_USERS_BY_ROLE{{
        this.command = new FindUsersByRole();
    }},
    /**
     * Represents find users by status command implementation of Command interface.
     */
    FIND_USERS_BY_STATUS{{
        this.command = new FindUsersByStatus();
    }},
    /**
     * Represents coefficients command implementation of Command interface.
     */
    COEFFICIENTS{{
        this.command = new CoefficientsPage();
    }},
    /**
     * Represents set coefficients command implementation of Command interface.
     */
    SET_COEFFICIENTS{{
        this.command = new SetCoefficients();
    }},
    /**
     * Represents load user profile command implementation of Command interface.
     */
    LOAD_USER_PROFILE{{
        this.command = new LoadUserProfileById();
    }},
    /**
     * Represents change user status command implementation of Command interface.
     */
    CHANGE_USER_STATUS{{
        this.command = new ChangeUserStatus();
    }},
    /**
     * Represents change role to librarian command implementation of Command interface.
     */
    CHANGE_ROLE_TO_LIBRARIAN{{
        this.command = new ChangeRoleToLibrarian();
    }},
    /**
     * Represents change role to reader command implementation of Command interface.
     */
    CHANGE_ROLE_TO_READER{{
        this.command = new ChangeRoleToReader();
    }},
    /**
     * Represents to librarian books command implementation of Command interface.
     */
    TO_LIBRARIAN_BOOKS{{
        this.command = new LibrarianBooksPage();
    }},
    /**
     * Represents add book command implementation of Command interface.
     */
    ADD_BOOK{{
        this.command = new AddBook();
    }},
    /**
     * Represents upload book cover command implementation of Command interface.
     */
    UPLOAD_BOOK_COVER{{
        this.command = new UploadBookCover();
    }},
    /**
     * Represents upload author photo command implementation of Command interface.
     */
    UPLOAD_AUTHOR_PHOTO{{
        this.command = new UploadAuthorPhoto();
    }},
    /**
     * Represents edit book command implementation of Command interface.
     */
    EDIT_BOOK{{
        this.command = new EditBook();
    }},
    /**
     * Represents delete book command implementation of Command interface.
     */
    DELETE_BOOK{{
        this.command = new DeleteBook();
    }},
    /**
     * Represents upload pdf command implementation of Command interface.
     */
    UPLOAD_PDF{{
        this.command = new UploadPdf();
    }},
    /**
     * Represents view pdf command implementation of Command interface.
     */
    VIEW_PDF{{
        this.command = new ViewPdf();
    }},
    /**
     * Represents rent book command implementation of Command interface.
     */
    RENT_BOOK{{
        this.command = new RentBook();
    }},
    /**
     * Represents book requests command implementation of Command interface.
     */
    BOOK_REQUESTS{{
        this.command = new BookRequests();
    }},
    /**
     * Represents find book requests by type command implementation of Command interface.
     */
    FIND_BOOK_REQUESTS_BY_TYPE{{
        this.command = new FindBookRequestsByType();
    }},
    /**
     * Represents find book requests by state command implementation of Command interface.
     */
    FIND_BOOK_REQUESTS_BY_STATE{{
        this.command = new FindBookRequestsByState();
    }},
    /**
     * Represents change book request state command implementation of Command interface.
     */
    CHANGE_BOOK_REQUEST_STATE{{
        this.command = new ChangeBookRequestState();
    }},
    /**
     * Represents my book requests command implementation of Command interface.
     */
    MY_BOOK_REQUESTS{{
        this.command = new MyBookRequests();
    }},
    /**
     * Represents return book command implementation of Command interface.
     */
    RETURN_BOOK{{
        this.command = new ReturnBook();
    }},
    /**
     * Represents reading room command implementation of Command interface.
     */
    READING_ROOM{{
        this.command = new ReadingRoom();
    }},
    /**
     * Represents find books by keyword command implementation of Command interface.
     */
    FIND_BOOKS_BY_KEYWORD{{
        this.command = new FindBooksByKeyword();
    }},
    /**
     * Represents find books by genre command implementation of Command interface.
     */
    FIND_BOOKS_BY_GENRE {{
        this.command = new FindBooksByGenre();
    }},
    /**
     * Represents find books by author command implementation of Command interface.
     */
    FIND_BOOKS_BY_AUTHOR{{
        this.command = new FindBooksByAuthor();
    }},
    /**
     * Represents delete book request command implementation of Command interface.
     */
    DELETE_BOOK_REQUEST{{
        this.command = new DeleteBookRequest();
    }},
    /**
     * Represents sort records command implementation of Command interface.
     */
    SORT_RECORDS {{
        this.command = new SortRecords();
    }},
    /**
     * Represents send report command implementation of Command interface.
     */
    SEND_REPORT {{
        this.command = new SendReport();
    }},
    /**
     * Represents user reports command implementation of Command interface.
     */
    USER_REPORTS {{
        this.command = new UserReports();
    }},
    /**
     * Represents load user report command implementation of Command interface.
     */
    LOAD_USER_REPORT {{
        this.command = new LoadUserReportById();
    }},
    /**
     * Represents create report response command implementation of Command interface.
     */
    CREATE_REPORT_RESPONSE{{
        this.command = new CreateUserReportResponse();
    }},
    /**
     * Represents find reports by state command implementation of Command interface.
     */
    FIND_REPORTS_BY_STATE{{
        this.command = new FindUserReportsByState();
    }};

    /**
     * Command object of command.
     */
    Command command;

    /**
     * Getter method of Command's implementation.
     *
     * @return Command object.
     */
    public Command getCurrentCommand() {
        return command;
    }
}