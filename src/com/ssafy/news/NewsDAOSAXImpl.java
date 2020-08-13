package com.ssafy.news;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NewsDAOSAXImpl implements INewsDAO{
	
	List<News> list = new ArrayList<News>();
	
	public class SaxHandler extends DefaultHandler{
		StringBuilder b = null;
		boolean flagT = false;
		boolean flagD = false;
		boolean flagI = false;		
		News N = new News();
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attr) 
			throws SAXException {
			
			if (qName.equals("item")) {
				flagI = true;
			} else if (qName.equals("title")) {
				flagT = true;
			} else if (qName.equals("description")) {
				flagD = true;
			}
			b = new StringBuilder();
		}
		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			b.append(new String(ch, start, length));
		}		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (flagT) {
				N.setTitle(b.toString());
				flagT = false;
			} else if (flagD) {
				N.setDesc(b.toString());
				flagD = false;
			} else if (qName.equals("item")) {
				list.add(N);				
				flagI = false;
				N = new News();
			}
		}
		
		public List<News> getNews() {
			return list;
		}
	}
	
	@Override
	public List<News> getNewsList(String url) {
		File file = new File(url);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			NewsDAOSAXImpl.SaxHandler handler = new SaxHandler();
			saxParser.parse(file, handler);					
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public News search(int index) {
		return null;
	}

}
