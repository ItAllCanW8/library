package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.Localization;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguage implements Command {
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        String newLocalizationStr = req.getParameter(RequestParameter.NEW_LOCALIZATION);
        Localization newLocalization = Localization.defineLocalization(newLocalizationStr);
        session.setAttribute(SessionAttribute.CURRENT_LOCALIZATION, newLocalization.getLocalization());
        return (new CommandResult(CommandResult.Type.RETURN_WITH_REDIRECT));
    }
}
