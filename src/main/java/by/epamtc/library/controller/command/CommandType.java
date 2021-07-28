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
    }};

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
