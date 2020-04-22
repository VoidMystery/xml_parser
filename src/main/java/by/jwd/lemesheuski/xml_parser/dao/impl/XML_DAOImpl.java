package by.jwd.lemesheuski.xml_parser.dao.impl;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.exception.DAOException;
import by.jwd.lemesheuski.xml_parser.dao.XML_DAO;
import by.jwd.lemesheuski.xml_parser.dao.parser.Parser;
import by.jwd.lemesheuski.xml_parser.dao.parser.ParserException;
import by.jwd.lemesheuski.xml_parser.dao.parser.ParserProvider;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XML_DAOImpl implements XML_DAO {
    @Override
    public List<Apartment> read(String uploadPath, String type, Part file) throws DAOException {
        List<Apartment> apartments;
        String fileName = file.getSubmittedFileName();
        try {
            Parser parser = ParserProvider.getInstance().getParser(type);
            apartments = parser.parse(uploadPath, fileName);
        } catch (IllegalArgumentException | ParserException e) {
            throw new DAOException(e);
        }
        return apartments;
    }

    @Override
    public boolean validate(String path, String type, Part file) throws DAOException {
        String fileName = file.getSubmittedFileName();
        try {
            Parser parser = ParserProvider.getInstance().getParser(type);
            return parser.validate(path, fileName);
        } catch (IllegalArgumentException | ParserException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void save(String path, Part file) throws DAOException {
        try {
            String fileName = file.getSubmittedFileName();
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.write(path + File.separator + fileName);
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(String path, Part file) {
        String fileName = file.getSubmittedFileName();
        File xml = new File(path + File.separator + fileName);
        xml.delete();
    }
}
