package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserReports implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserReportService service = ServiceFactory.getInstance().getUserReportService();
        CommandResult result = new CommandResult(PagePath.USER_REPORTS, CommandResult.Type.FORWARD);
        try {
            List<UserReport> reports = service.loadUserReports();
            if (reports.size() > 0) {
                req.setAttribute(RequestParameter.USER_REPORTS, reports);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}