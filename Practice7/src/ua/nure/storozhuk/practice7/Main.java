package ua.nure.storozhuk.practice7;

import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		STAXParser staxBuilder = new STAXParser();
		staxBuilder.buildSetOldCards("input.xml");
		List<OldCard> list = staxBuilder.getCards();
		Sorter sort = new Sorter();
		Collections.sort(list, sort.yearComp());
		XMLWriter.createXML("output.stax.xml", list);
		DOMParser dom = new DOMParser();
		dom.buildCards("input.xml");
		list = dom.getCards();
		Collections.sort(list, sort.authorComp());
		XMLWriter.createXML("output.dom.xml", list);
		SAXParser saxBuilder = new SAXParser();
		saxBuilder.buildSetOldCards("input.xml");
		list = saxBuilder.getCards();
		Collections.sort(list, sort.typeComp());
		XMLWriter.createXML("output.sax.xml", list);
	}

}
