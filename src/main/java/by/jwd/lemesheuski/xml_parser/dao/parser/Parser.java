package by.jwd.lemesheuski.xml_parser.dao.parser;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Apartment> parse(String path, String filename) throws ParserException;
    boolean validate(String path, String filename) throws ParserException;
    
    default void validateCommon(String path, Source source)throws IOException, SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaLocation = new File(path + File.separator + "apartment.xsd");

        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();

        validator.validate(source);
    }
}
