package by.epamtc.library.controller.command;

import by.epamtc.library.controller.command.impl.Register;

public enum CommandType {
    REGISTER {{
        this.command = new Register();
    }};
//    LOGIN {{
//        this.command = new LoginCommand();
//    }};

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
