package Service;

import Model.BookModel;

public class Main {

	public static void main(String[] args) {
		String txtFilePath = "txt/sample-lorem-ipsum-text-file.txt";
		String xmlFilePath = "xml/book.xml";

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
	}
}
