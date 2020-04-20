package by.jwd.lemesheuski.xml_parser.dao.parser.satx;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.parser.ApartmentTagName;
import by.jwd.lemesheuski.xml_parser.dao.parser.Parser;
import by.jwd.lemesheuski.xml_parser.dao.parser.ParserException;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StAXUtil implements Parser {
    public List<Apartment> parse(String path, String filename) throws ParserException {
        List<Apartment> apartments;
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(path + File.separator + filename);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            apartments = process(reader);
            return apartments;
        } catch (XMLStreamException | IOException e) {
            throw new ParserException(e);
        }
    }

    @Override
    public boolean validate(String path, String filename) throws ParserException {
        boolean status = true;
        try {
            File xml = new File(path + File.separator + filename);

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(new FileInputStream(xml));

            Source source = new StAXSource(reader);

            validateCommon(path, source);
        } catch (SAXException e) {
            status = false;
            System.out.println(e.getMessage());
        } catch (XMLStreamException | IOException e) {
            throw new ParserException(e);
        }
        return status;
    }

    private List<Apartment> process(XMLStreamReader reader) throws XMLStreamException {
        List<Apartment> apartmentList = new ArrayList<>();
        Apartment apartment = null;
        ApartmentTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = ApartmentTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (elementName) {
                        case APARTMENT:
                            apartment = new Apartment();
                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            apartment.setId(id);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case ROOM:
                            apartment.setRoom_number(Integer.parseInt(text));
                            break;
                        case FLOOR:
                            apartment.setFloor(Integer.parseInt(text));
                            break;
                        case BALCONY:
                            apartment.setBalcony(Boolean.parseBoolean(text));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = ApartmentTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (elementName) {
                        case APARTMENT:
                            apartmentList.add(apartment);
                    }
                    break;
            }
        }
        return apartmentList;
    }
}
