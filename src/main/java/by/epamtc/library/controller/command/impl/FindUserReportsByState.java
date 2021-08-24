package by.epamtc.library.controller.command.impl;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUserReportsByState implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String isProcessedStr = req.getParameter(RequestParameter.USER_REPORT_STATE);
        CommandResult result = new CommandResult(PagePath.USER_REPORTS, CommandResult.Type.FORWARD);

        try{
            UserReportService service = ServiceFactory.getInstance().getUserReportService();
            List<UserReport> reports = service.findReportsByState(Integer.parseInt(isProcessedStr) == 1);
            if (reports.size() > 0) {
                req.setAttribute(RequestParameter.USER_REPORTS, reports);
            }
        } catch (NumberFormatException e){
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
        }
        catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}
