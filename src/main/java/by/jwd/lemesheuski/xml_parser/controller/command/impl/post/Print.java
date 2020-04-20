package by.jwd.lemesheuski.xml_parser.controller.command.impl.post;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.controller.JspPageName;
import by.jwd.lemesheuski.xml_parser.controller.command.CommandException;
import by.jwd.lemesheuski.xml_parser.controller.command.ICommand;
import by.jwd.lemesheuski.xml_parser.service.ServiceException;
import by.jwd.lemesheuski.xml_parser.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class Print implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException {
        try {
            String uploadPath = request.getServletContext().getRealPath("") + "../Uploads";
            String parserType = request.getParameter("parser_type");
            Part file = request.getPart("file");
            List<Apartment> apartments = ServiceProvider.getInstance().getFileService().read(uploadPath, parserType, file);
            if(apartments!=null){
                request.setAttribute("apartments", apartments);
            }else{
                request.setAttribute("validationError", true);
            }

        } catch (IOException | ServiceException e) {
            throw new CommandException(e);
        }
        return JspPageName.PRINT_PAGE;
    }
}
