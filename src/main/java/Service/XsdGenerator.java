package Service;

import Model.BookModel;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

public class XsdGenerator {

	public static void xsdGenerator() throws IOException {

		try {
			String xsdFileName = "xml/book-schema.xsd";
			// Create a JAXBContext for the class(es) you want to generate XSD for
			JAXBContext context = JAXBContext.newInstance(BookModel.class);

			// Use SchemaOutputResolver to generate the XSD schema
			context.generateSchema(new MySchemaOutputResolver(xsdFileName));
			System.out.println("xsd created");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	// Custom implementation of SchemaOutputResolver to output the XSD file
	static class MySchemaOutputResolver extends SchemaOutputResolver {

		private String xsdFileName;

		public MySchemaOutputResolver(String xsdFileName) {
			this.xsdFileName = xsdFileName;
		}

		@Override
		public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
			// Specify the location and file where the XSD will be saved
			File file = new File(xsdFileName);

			// Create a StreamResult to write the schema to the file
			StreamResult result = new StreamResult(file);

			// Return the StreamResult with systemId set to file's absolute path
			result.setSystemId(file.toURI().toString());

			return result;
		}
	}
}
