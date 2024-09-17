package Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLOutputFactory;

public class SelectedParagraphsFromXml1 {

    public static List<String> SelectedParagraphsFromXml(String xmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<String> selectedParagraphs = new ArrayList<>();
        int currentParagraph = 0;
        boolean inParagraph = false;
        StringBuilder paragraphContent = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = xmlStreamReader.getLocalName();
                        if (elementName.equals("paragraph")) {
                            currentParagraph++;
                            if (currentParagraph >= 3 && currentParagraph <= 7) {
                                inParagraph = true;
                                paragraphContent = new StringBuilder(); // Start new paragraph
                            } else {
                                skipElements(xmlStreamReader); // Skip this paragraph
                            }
                        }
                        if (inParagraph) {
                            if (!elementName.equals("paragraph")) {
                                paragraphContent.append("<").append(elementName).append(">");
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        elementName = xmlStreamReader.getLocalName();
                        if (elementName.equals("paragraph") && inParagraph) {
                            selectedParagraphs.add(paragraphContent.toString()); // Add paragraph content to list
                            inParagraph = false;
                        } else if (inParagraph) {
                            if (!elementName.equals("paragraph")) {
                                paragraphContent.append("</").append(elementName).append(">");
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if (inParagraph) {
                            paragraphContent.append(xmlStreamReader.getText().trim());
                        }
                        break;
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.getMessage();
        }
        return selectedParagraphs;
    }

    public static void writeSelectedParagraphsToXml(String xmlFile, List<String> paragraphs) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        try (FileOutputStream fileOutputStream = new FileOutputStream(xmlFile)) {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "UTF-8");

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("book");

            for (String paragraph : paragraphs) {
                xmlStreamWriter.writeStartElement("paragraph");
                xmlStreamWriter.writeCharacters(paragraph);
                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            System.out.println("Selected paragraphs 3 to 7 writted to new XML!");

        } catch (XMLStreamException | IOException e) {
            e.getMessage();
        }
    }

    public static void skipElements(XMLStreamReader reader) throws XMLStreamException {
        int depth = 1;
        while (reader.hasNext() && depth > 0) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                depth++;
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                depth--;
            }
        }
    }
public static int countParagraphs(String xmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        int paragraphCount = 0;

        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (xmlStreamReader.getLocalName().equals("paragraph")) {
                        paragraphCount++;
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.getMessage();
        }

        return paragraphCount;
    }


}
