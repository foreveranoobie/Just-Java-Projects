package ua.nure.storozhuk.practice7;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class XMLWriter {
	private XMLWriter() {
	}

	public static void createXML(String fileDir, Iterable<OldCard> list) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		documentBuilderFactory.setNamespaceAware(true);
		Document document = documentBuilder.newDocument();
		String root = "OldCards";
		Element rootElement;
		rootElement = document.createElement(root);
		rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation",
				"input-no-targetNamespace.xsd");
		document.appendChild(rootElement);
		for (OldCard card : list) {
			Element oldCard = document.createElement("OldCard");
			rootElement.appendChild(oldCard);

			Element emThema = document.createElement("Thema");
			String name = card.getThema();
			emThema.appendChild(document.createTextNode(name));
			oldCard.appendChild(emThema);

			Element emType = document.createElement("Type");
			String type = card.getType();
			emType.appendChild(document.createTextNode(type));
			oldCard.appendChild(emType);

			Element emCountry = document.createElement("Country");
			String country = card.getCountry();
			emCountry.appendChild(document.createTextNode(country));
			oldCard.appendChild(emCountry);

			Element emYear = document.createElement("Year");
			String year = String.valueOf(card.getYear());
			emYear.appendChild(document.createTextNode(year));
			oldCard.appendChild(emYear);

			if (card.getAuthor() != null) {
				Element emAuthor = document.createElement("Author");
				String author = card.getAuthor();
				emAuthor.appendChild(document.createTextNode(author));
				oldCard.appendChild(emAuthor);
			}
			Element emValuable = document.createElement("Valuable");
			String valuable = card.getValuable();
			emValuable.appendChild(document.createTextNode(valuable));
			oldCard.appendChild(emValuable);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileWriter(fileDir));
			transformer.transform(source, result);
		} catch (IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
}
