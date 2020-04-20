package by.jwd.lemesheuski.xml_parser.dao;

import by.jwd.lemesheuski.xml_parser.dao.impl.XML_DAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private DAOProvider(){}

    public static DAOProvider getInstance() {
        return instance;
    }

    private final XML_DAO xmlDAO = new XML_DAOImpl();

    public XML_DAO getXML_DAO(){
        return xmlDAO;
    }
}
