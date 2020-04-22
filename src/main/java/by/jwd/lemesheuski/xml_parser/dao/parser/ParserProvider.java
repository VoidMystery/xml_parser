package by.jwd.lemesheuski.xml_parser.dao.parser;

import by.jwd.lemesheuski.xml_parser.dao.XMLParserType;
import by.jwd.lemesheuski.xml_parser.dao.parser.dom.DOMUtil;
import by.jwd.lemesheuski.xml_parser.dao.parser.satx.StAXUtil;
import by.jwd.lemesheuski.xml_parser.dao.parser.sax.SAXUtil;

import java.util.HashMap;
import java.util.Map;

public class ParserProvider {
    private static final ParserProvider instance = new ParserProvider();
    private Map<XMLParserType, Parser> parsers = new HashMap<>();

    private ParserProvider() {
        parsers.put(XMLParserType.SAX, new SAXUtil());
        parsers.put(XMLParserType.STAX, new StAXUtil());
        parsers.put(XMLParserType.DOM, new DOMUtil());
    }

    public static ParserProvider getInstance() {
        return instance;
    }

    public Parser getParser(String parserType) {
        Parser parser;

        if (parserType != null) {
            XMLParserType type = XMLParserType.valueOf(parserType.toUpperCase());
            parser = parsers.get(type);
        } else {
            throw new IllegalArgumentException();
        }

        return parser;
    }
}
