package by.jwd.lemesheuski.xml_parser.dao.parser.dom;

import by.jwd.lemesheuski.xml_parser.bean.Apartment;
import by.jwd.lemesheuski.xml_parser.dao.parser.Parser;
import by.jwd.lemesheuski.xml_parser.dao.parser.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMUtil implements Parser {
    @Override
    public List<Apartment> parse(String path, String filename) throws ParserException {
        File xml = new File(path + File.separator + filename);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);

            return getApartmentList(document);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException(e);
        }
    }

    private List<Apartment> getApartmentList(Document document) {
        List<Apartment> apartments = new ArrayList<>();
        NodeList apartmentNodes = document.getElementsByTagName("apartment");
        Apartment apartment;
        for (int i = 0; i < apartmentNodes.getLength(); i++) {
            apartment = new Apartment();
            Element apartmentElement = (Element) apartmentNodes.item(i);
            apartment.setId(Integer.parseInt(apartmentElement.getAttribute("id")));
            apartment.setRoom_number(
                    Integer.parseInt(getSingleChild(apartmentElement, "room").getTextContent().trim()));
            apartment.setFloor(
                    Integer.parseInt(getSingleChild(apartmentElement, "floor").getTextContent().trim()));
            apartment.setBalcony(
                    Boolean.parseBoolean(getSingleChild(apartmentElement, "balcony").getTextContent().trim()));
            apartments.add(apartment);
        }
        return apartments;
    }

    private Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

    @Override
    public boolean validate(String path, String filename) throws ParserException {
        boolean status = true;
        try {

            File xml = new File(path + File.separator + filename);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            DOMSource source = new DOMSource(document);

            validateCommon(path, source);
        } catch (SAXException e) {
            status = false;
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | IOException e) {
            throw new ParserException(e);
        }
        return status;
    }
}
