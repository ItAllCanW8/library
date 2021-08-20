package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
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
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoadUserReportById implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String reportIdStr = req.getParameter(RequestParameter.USER_REPORT_ID);
        UserReportService service = ServiceFactory.getInstance().getUserReportService();
        CommandResult result = new CommandResult(CommandName.USER_REPORTS, CommandResult.Type.FORWARD);
        try {
            long reportId = Long.parseLong(reportIdStr);
            Optional<UserReport> reportOptional = service.findUserReportById(reportId);

            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                req.setAttribute(RequestParameter.USER_REPORT, report);
                HttpSession session = req.getSession();
                session.setAttribute(RequestParameter.USER_REPORT_ID, reportId);
                result = new CommandResult(PagePath.USER_REPORT, CommandResult.Type.FORWARD);
            }
        } catch (NumberFormatException e) {
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
