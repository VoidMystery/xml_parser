package by.jwd.lemesheuski.xml_parser.controller;

import by.jwd.lemesheuski.xml_parser.controller.command.CommandException;
import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandHelper;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.CommandHelperProvider;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.RequestParameterName;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.impl.GetCommandHelper;
import by.jwd.lemesheuski.xml_parser.controller.command_helper.impl.PostCommandHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GetCommandHelper getCommandHelper = CommandHelperProvider.getInstance().getGetCommandHelper();
        requestHandler(getCommandHelper, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostCommandHelper postCommandHelper = CommandHelperProvider.getInstance().getPostCommandHelper();
        requestHandler(postCommandHelper, request, response);
    }

    private void requestHandler(CommandHelper commandHelper, HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = commandHelper.getCommand(commandName);
        String page;
        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher == null) {
            page = JspPageName.ERROR_PAGE;
            dispatcher = request.getRequestDispatcher(page);
        }
        dispatcher.forward(request, response);

    }


}
