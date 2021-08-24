package by.epamtc.library.controller.command;

import by.epamtc.library.controller.command.impl.*;

public enum CommandType {
    HOME {{
        this.command = new Home();
    }},
    REGISTER {{
        this.command = new Register();
    }},
    LOGIN {{
        this.command = new Login();
    }},
    LOGOUT {{
        this.command = new Logout();
    }},
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguage();
    }},
    LOAD_BOOKS {{
        this.command = new LoadBooks();
    }},
    LOAD_BOOK_INFO{{
        this.command = new LoadBookInfo();
    }},
    USER_PROFILE{{
        this.command = new UserProfile();
    }},
    UPLOAD_PHOTO{{
        this.command = new UploadPhoto();
    }},
    LOAD_PROFILE_PHOTO{{
        this.command = new LoadProfilePhoto();
    }},
    LOAD_BOOK_COVER{{
        this.command = new LoadBookCover();
    }},
    EDIT_USER_PROFILE{{
        this.command = new EditUserProfile();
    }},
    CHANGE_PASSWORD{{
        this.command = new ChangePassword();
    }},
    DEACTIVATE_ACCOUNT{{
        this.command = new DeactivateAccount();
    }},
    USERS{{
        this.command = new Users();
    }},
    FIND_USERS_BY_ROLE{{
        this.command = new FindUsersByRole();
    }},
    FIND_USERS_BY_STATUS{{
        this.command = new FindUsersByStatus();
    }},
    COEFFICIENTS{{
        this.command = new CoefficientsPage();
    }},
    SET_COEFFICIENTS{{
        this.command = new SetCoefficients();
    }},
    LOAD_USER_PROFILE{{
        this.command = new LoadUserProfileById();
    }},
    CHANGE_USER_STATUS{{
        this.command = new ChangeUserStatus();
    }},
    CHANGE_ROLE_TO_LIBRARIAN{{
        this.command = new ChangeRoleToLibrarian();
    }},
    CHANGE_ROLE_TO_READER{{
        this.command = new ChangeRoleToReader();
    }},
    TO_LIBRARIAN_BOOKS{{
        this.command = new LibrarianBooksPage();
    }},
    ADD_BOOK{{
        this.command = new AddBook();
    }},
    UPLOAD_BOOK_COVER{{
        this.command = new UploadBookCover();
    }},
    UPLOAD_AUTHOR_PHOTO{{
        this.command = new UploadAuthorPhoto();
    }},
    EDIT_BOOK{{
        this.command = new EditBook();
    }},
    DELETE_BOOK{{
        this.command = new DeleteBook();
    }},
    UPLOAD_PDF{{
        this.command = new UploadPdf();
    }},
    VIEW_PDF{{
        this.command = new ViewPdf();
    }},
    RENT_BOOK{{
        this.command = new RentBook();
    }},
    BOOK_REQUESTS{{
        this.command = new BookRequests();
    }},
    FIND_BOOK_REQUESTS_BY_TYPE{{
        this.command = new FindBookRequestsByType();
    }},
    FIND_BOOK_REQUESTS_BY_STATE{{
        this.command = new FindBookRequestsByState();
    }},
    CHANGE_BOOK_REQUEST_STATE{{
        this.command = new ChangeBookRequestState();
    }},
    MY_BOOK_REQUESTS{{
        this.command = new MyBookRequests();
    }},
    RETURN_BOOK{{
        this.command = new ReturnBook();
    }},
    READING_ROOM{{
        this.command = new ReadingRoom();
    }},
    FIND_BOOKS_BY_KEYWORD{{
        this.command = new FindBooksByKeyword();
    }},
    FIND_BOOKS_BY_GENRE {{
        this.command = new FindBooksByGenre();
    }},
    FIND_BOOKS_BY_AUTHOR{{
        this.command = new FindBooksByAuthor();
    }},
    DELETE_BOOK_REQUEST{{
        this.command = new DeleteBookRequest();
    }},
    SORT_RECORDS {{
        this.command = new SortRecords();
    }},
    SEND_REPORT {{
        this.command = new SendReport();
    }},
    USER_REPORTS {{
        this.command = new UserReports();
    }},
    LOAD_USER_REPORT {{
        this.command = new LoadUserReportById();
    }},
    CREATE_REPORT_RESPONSE{{
        this.command = new CreateUserReportResponse();
    }},
    FIND_REPORTS_BY_STATE{{
        this.command = new FindUserReportsByState();
    }};

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}