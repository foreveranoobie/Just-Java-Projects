package ua.nure.storozhuk.practice7;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXParser {
	private List<OldCard> oldCards;
	private OldCardHandler och;
	private XMLReader reader;

	public SAXParser() {
		och = new OldCardHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(och);
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public List<OldCard> getCards() {
		return oldCards;
	}

	public void buildSetOldCards(String fileName) {
		try {
			reader.parse(fileName);
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}
		oldCards = och.getCards();
	}
}
