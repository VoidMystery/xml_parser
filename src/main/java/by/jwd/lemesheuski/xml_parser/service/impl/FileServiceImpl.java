package by.jwd.lemesheuski.xml_parser.service.impl;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.XML_DAO;
import by.jwd.lemesheuski.xml_parser.dao.exception.DAOException;
import by.jwd.lemesheuski.xml_parser.service.FileService;
import by.jwd.lemesheuski.xml_parser.service.ServiceException;
import by.jwd.lemesheuski.xml_parser.dao.DAOProvider;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    XML_DAO xml_dao = DAOProvider.getInstance().getXML_DAO();

    @Override
    public List<Apartment> read(String uploadPath, String parserType, Part file) throws ServiceException {
        List<Apartment> apartments = new ArrayList<>();
        if (parserType != null && !parserType.isEmpty() && file != null) {
            try {
                xml_dao.save(uploadPath, file);
                if (!xml_dao.validate(uploadPath, parserType, file)) {
                    return null;
                }
                apartments = xml_dao.read(uploadPath, parserType, file);
            } catch (DAOException e) {
                throw new ServiceException(e);
            } finally {
                xml_dao.delete(uploadPath, file);
            }
        }
        return apartments;
    }
}
