package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Home implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)  {
//        FacultyService facultyService = ServiceFactory.getInstance().getFacultyService();
//        List<Faculty> bestFaculties = facultyService.retrieveBestFaculties();
//        request.setAttribute(RequestAttribute.BEST_FACULTIES, bestFaculties);
        return new CommandResult("WEB-INF/jsp/home/home.jsp", RoutingType.FORWARD);
    }
}
