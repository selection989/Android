package com.thenewboston.terry;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetXmlData extends DefaultHandler {

	public Weather weather = new Weather();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("city")) {
			weather.setCity(attributes.getValue("data"));

		} else if (localName.equals("temp_c")) {
			int temp = Integer.parseInt(attributes.getValue("data"));
			weather.setTemperature(temp);

		}
	}

	public String getData() {

		return weather.toString();
	}

}
