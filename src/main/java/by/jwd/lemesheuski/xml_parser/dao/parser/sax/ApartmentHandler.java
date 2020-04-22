package by.jwd.lemesheuski.xml_parser.dao.parser.sax;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.parser.ApartmentTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ApartmentHandler extends DefaultHandler {
    private List<Apartment> apartmentList = new ArrayList<>();
    private Apartment apartment;
    private StringBuilder text;

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (localName.equals("apartment")) {
            apartment = new Apartment();
            apartment.setId((Integer.parseInt(attributes.getValue("id"))));
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        ApartmentTagName tagName = ApartmentTagName.valueOf(localName.toUpperCase().replace("-", "_"));
        switch (tagName) {
            case FLOOR:
                apartment.setFloor(Integer.parseInt(text.toString()));
                break;
            case BALCONY:
                apartment.setBalcony(Boolean.parseBoolean(text.toString()));
                break;
            case ROOM:
                apartment.setRoom_number(Integer.parseInt(text.toString()));
                break;
            case APARTMENT:
                apartmentList.add(apartment);
                apartment = null;
                break;
        }
    }
}
