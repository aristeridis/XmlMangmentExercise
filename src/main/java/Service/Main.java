package Service;

import Model.BookModel;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.modelmbean.XMLParseException;

public class Main {

	public static void main(String[] args) {
		String txtFilePath = "txt/sample-lorem-ipsum-text-file.txt";
		String xmlFilePath = "xml/book.xml";
		String xsdFilePath = "xml/book-schema.xsd";
		String selectedXmlFile = "xml/paragraphs3To7.xml";
		try {
			BookModel book = TxtToObject.TxtToObject(txtFilePath);

			if (book != null) {
				StaxObjToXml.staxObjToXML(book, xmlFilePath);
				System.out.println("XML file created on main method");
			} else {
				System.out.println("Failed to create book object");
			}
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			List<String> paragraphs = SelectedParagraphsFromXml.SelectedParagraphsFromXml(xmlFilePath);
			SelectedParagraphsFromXml.writeSelectedParagraphsToXml(selectedXmlFile, paragraphs);
			int numberOfParagraphs = SelectedParagraphsFromXml.countParagraphs(selectedXmlFile);
			System.out.println("Number of paragraphs " + numberOfParagraphs);

		} catch (NullPointerException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			XsdGenerator.xsdGenerator();
//		try {
//		boolean validity = XmlValidator.validateXml(xmlFilePath ,xsdFilePath);
//		  if (validity)
//            System.out.println("The file is valid");
//     else
//            System.out.println("The file is not valid");
//
//		} catch (Exception e) {
//			e.getMessage();
//		}

		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
