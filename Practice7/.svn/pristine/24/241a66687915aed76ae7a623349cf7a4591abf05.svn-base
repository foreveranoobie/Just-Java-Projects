package ua.nure.storozhuk.practice7;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {
	private DocumentBuilder docBuilder;
	private List<OldCard> oldCards;

	public DOMParser() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		oldCards = new LinkedList<>();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Parses configuration error: " + e);
		}
	}

	public List<OldCard> getCards() {
		return oldCards;
	}

	public void buildCards(String filename) {
		Document doc = null;
		try {
			doc = docBuilder.parse(filename);
			Element root = doc.getDocumentElement();
			NodeList cardsList = root.getElementsByTagName("OldCard");
			for (int i = 0; i < cardsList.getLength(); i++) {
				Element cardElement = (Element) cardsList.item(i);
				oldCards.add(buildCard(cardElement));
			}
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private static OldCard buildCard(Element cardElement) {
		OldCard oldCard = new OldCard();
		String author = getElementTextContent(cardElement, "Author");
		if (author != null) {
			oldCard.setAuthor(author);
		}
		oldCard.setType(Type.valueOf(getElementTextContent(cardElement, "Type").toUpperCase(Locale.getDefault())));
		oldCard.setValuable(Valuable.valueOf(getElementTextContent(cardElement, "Valuable")
				.toUpperCase(Locale.getDefault())));
		oldCard.setCountry(getElementTextContent(cardElement, "Country"));
		oldCard.setThema(getElementTextContent(cardElement, "Thema"));
		oldCard.setYear(Integer.valueOf(getElementTextContent(cardElement, "Year")));
		return oldCard;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		org.w3c.dom.Node node = nList.item(0);
		if (node != null) {
			return node.getTextContent();
		}
		return null;
	}
}
