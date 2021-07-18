package by.epamtc.library.controller.command.factory;

import by.epamtc.library.controller.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    private CommandFactory() {
//        commands.put(Commands.LOGIN_COMMAND, new LoginCommand());
//        commands.put(Commands.LOGOUT_COMMAND, new LogoutCommand());
//        commands.put(Commands.SHOW_PAGE_COMMAND, new ShowPageCommand());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
