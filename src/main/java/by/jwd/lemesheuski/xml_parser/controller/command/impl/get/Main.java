package by.jwd.lemesheuski.xml_parser.controller.command.impl.get;

import by.jwd.lemesheuski.xml_parser.controller.JspPageName;
import by.jwd.lemesheuski.xml_parser.controller.command.CommandException;
import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return JspPageName.MAIN_PAGE;
    }
}
