package Service;

import Model.BookModel;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String txtFilePath = "txt/sample-lorem-ipsum-text-file.txt";
		String xmlFilePath = "xml/book.xml";
        String selectedXmlFile = "xml/paragraphs3To7.xml";
		try {
	List<String> paragraphs = SelectedParagraphsFromXml.SelectedParagraphsFromXml(xmlFilePath);
        SelectedParagraphsFromXml.writeSelectedParagraphsToXml(selectedXmlFile, paragraphs);
	  int numberOfParagraphs = SelectedParagraphsFromXml.countParagraphs(selectedXmlFile);
        System.out.println("Number of paragraphs " + numberOfParagraphs);

   
		} catch (NullPointerException e) {
			e.getMessage();
		}
		catch (Exception e) {
			e.getMessage();
		}
       
		try {
			BookModel book = TxtToObject.TxtToObject(txtFilePath);

			if (book != null) {
				StaxObjToXml.staxObjToXML(book, xmlFilePath);
				System.out.println("XML file created on main method");
			} else {
				System.out.println("Failed to create book object");
			}
		} catch (Exception e ) {
			e.getMessage();
		}
		       // extractParagraphs("xml/book.xml", "xml/paragraphs3To7.xml");

	}
}
