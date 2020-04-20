package by.jwd.lemesheuski.xml_parser.dao.parser.sax;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.parser.Parser;
import by.jwd.lemesheuski.xml_parser.dao.parser.ParserException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SAXUtil implements Parser {

    @Override
    public List<Apartment> parse(String path, String filename) throws ParserException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();

            ApartmentHandler handler = new ApartmentHandler();

            saxParser.parse(new File(path + File.separator + filename), handler);

            return handler.getApartmentList();
        }catch (ParserConfigurationException| SAXException| IOException e){
            throw new ParserException(e);
        }

    }

    @Override
    public boolean validate(String path, String filename) throws ParserException {
        boolean status = true;
        try {

            File xml = new File(path + File.separator + filename);

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();

            Source source = new SAXSource(saxParser.getXMLReader(), new InputSource(new FileReader(xml)));

            validateCommon(path, source);
        } catch (SAXException e) {
            status = false;
            System.out.println(e.getMessage());
        }catch (ParserConfigurationException | IOException e){
            throw new ParserException(e);
        }
        return status;
    }
}
