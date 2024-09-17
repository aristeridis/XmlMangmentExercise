package Service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    public static boolean validateXml(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFilePath)));

            System.out.println("XML is valid ");
            return true;
        } catch (SAXException | IOException e) {
            // If there is an error, it means XML is not valid
            System.err.println("XML is not valid");
            e.printStackTrace();
            return false;
        }
    }
}
