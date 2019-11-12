package ua.nure.storozhuk.practice7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXParser {
	private LinkedList<OldCard> oldCards = new LinkedList<>();
	private XMLInputFactory inputFactory;

	public STAXParser() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public List<OldCard> getCards() {
		return oldCards;
	}

	public void buildSetOldCards(String fileName) {
		XMLStreamReader reader = null;
		String name;
		try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (OldCardEnum.valueOf(name.toUpperCase(Locale.getDefault())) == OldCardEnum.OLDCARD) {
						OldCard card = buildCard(reader);
						oldCards.add(card);
					}
				}
			}
		} catch (IOException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private OldCard buildCard(XMLStreamReader reader) throws XMLStreamException {
		OldCard card = new OldCard();
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				innerSwitch(reader, name, card);
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (OldCardEnum.valueOf(name.toUpperCase(Locale.getDefault())) == OldCardEnum.OLDCARD) {
					return card;
				}
				break;
			default:
				break;
			}
		}
		return card;
	}
	
	private void innerSwitch(XMLStreamReader reader, String name, OldCard card) {
		switch (OldCardEnum.valueOf(name.toUpperCase(Locale.getDefault()))) {
		case TYPE:
			try {
				card.setType(Type.valueOf(reader.getElementText().toUpperCase(Locale.getDefault())));
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case VALUABLE:
			try {
				card.setValuable(Valuable.valueOf(reader.getElementText().toUpperCase(Locale.getDefault())));
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case YEAR:
			try {
				card.setYear(Integer.parseInt(reader.getElementText()));
			} catch (NumberFormatException | XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case COUNTRY:
			try {
				card.setCountry(reader.getElementText());
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case AUTHOR:
			try {
				card.setAuthor(reader.getElementText());
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case THEMA:
			try {
				card.setThema(reader.getElementText());
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
