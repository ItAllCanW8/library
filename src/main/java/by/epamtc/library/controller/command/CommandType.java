package by.epamtc.library.controller.command;

import by.epamtc.library.controller.command.impl.*;

public enum CommandType {
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
    }};

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
