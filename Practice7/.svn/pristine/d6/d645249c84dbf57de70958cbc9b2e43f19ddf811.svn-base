package ua.nure.storozhuk.practice7;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class OldCardHandler extends DefaultHandler {
	private List<OldCard> oldCards;
	private OldCardEnum currentEnum;
	private OldCard current;
	private EnumSet<OldCardEnum> withText;

	public OldCardHandler() {
		oldCards = new LinkedList<>();
		withText = EnumSet.range(OldCardEnum.THEMA, OldCardEnum.TYPE);
	}

	public List<OldCard> getCards() {
		return oldCards;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		if ("OldCard".equals(localName)) {
			current = new OldCard();
		} else {
			OldCardEnum temp = OldCardEnum.valueOf(localName.toUpperCase(Locale.getDefault()));
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("OldCard".equals(localName)) {
			oldCards.add(current);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case THEMA:
				current.setThema(s);
				break;
			case YEAR:
				current.setYear(Integer.parseInt(s));
				break;
			case VALUABLE:
				current.setValuable(Valuable.valueOf(s.toUpperCase(Locale.getDefault())));
				break;
			case TYPE:
				current.setType(Type.valueOf(s.toUpperCase(Locale.getDefault())));
				break;
			case AUTHOR:
				current.setAuthor(s);
				break;
			case COUNTRY:
				current.setCountry(s);
				break;
			default:
				throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
}
