package by.jwd.lemesheuski.xml_parser.service;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;

import javax.servlet.http.Part;
import java.util.List;

public interface FileService {
    List<Apartment> read(String uploadPath, String parserType, Part file) throws ServiceException;
}
