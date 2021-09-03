package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.Message;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command that creates response to user report.
 *
 * @author Artur Mironchik
 */
public class CreateUserReportResponse implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userReportResponse = req.getParameter(RequestParameter.USER_REPORT_RESPONSE);
        String reportIdStr = req.getParameter(RequestParameter.USER_REPORT_ID);

        UserReportService service = ServiceFactory.getInstance().getUserReportService();
        CommandResult result = new CommandResult(CommandName.USER_REPORT + reportIdStr, CommandResult.Type.REDIRECT);
        try {
            long reportId = Long.parseLong(reportIdStr);
            if (service.createResponse(reportId, userReportResponse)) {
                UserReport userReport = service.findById(reportId).get();
                User user = userReport.getUser();

                MailSender sender = MailSender.getInstance();
                sender.setupLetter(user.getEmail(), Message.LIBRARY_LETTER_SUBJECT, Message.HELLO_PREFIX + user.getUsername()
                        + Message.USER_REPORT_RESPONSE + userReportResponse);
                sender.send();
            } else {
                req.setAttribute(RequestParameter.USER_REPORT_RESPONSE, JspAttribute.INVALID_INPUT_DATA_MSG);
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);

                result = new CommandResult(CommandName.USER_REPORT + reportIdStr, CommandResult.Type.FORWARD);
            }
        } catch (NumberFormatException e) {
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            result = new CommandResult(CommandName.USER_REPORTS, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
