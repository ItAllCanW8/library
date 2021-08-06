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
    LOAD_USER_PROFILE{{
        this.command = new LoadUserProfileById();
    }},
    DEACTIVATE_USER_ACCOUNT{{
        this.command = new DeactivateUserById();
    }},
    ACTIVATE_USER_ACCOUNT{{
        this.command = new ActivateUserById();
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
    }};

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
