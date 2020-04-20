package by.jwd.lemesheuski.xml_parser.dao;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.exception.DAOException;

import javax.servlet.http.Part;
import java.util.List;

public interface XML_DAO {
    void save(String path, Part file) throws DAOException;
    void delete(String path, Part file);
    List<Apartment> read(String uploadPath, String type, Part file) throws DAOException;
    boolean validate(String path, String type, Part file) throws DAOException;
}
