package Service;

import Model.BookModel;
import Model.ChapterModel;
import Model.LineModel;
import Model.ParagraphModel;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class StaxObjToXml {

    public static void staxObjToXML(BookModel book, String xmlFilePath) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try (FileOutputStream fileOutputStream = new FileOutputStream(xmlFilePath)) {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "UTF-8");

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("book");

            xmlStreamWriter.writeStartElement("author");
            xmlStreamWriter.writeCharacters(book.getAuthor());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("creationDate");
            xmlStreamWriter.writeCharacters(book.getCreationDate().toString());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("numParagraphs");
            xmlStreamWriter.writeCharacters(String.valueOf(book.getNumParagraphs()));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("numLines");
            xmlStreamWriter.writeCharacters(String.valueOf(book.getNumLines()));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("words");
            xmlStreamWriter.writeCharacters(String.valueOf(book.getWords()));
            xmlStreamWriter.writeEndElement();

      
            xmlStreamWriter.writeStartElement("chapters");
            for (ChapterModel chapter : book.getChapters()) {
                xmlStreamWriter.writeStartElement("chapter");
                xmlStreamWriter.writeAttribute("number", String.valueOf(chapter.getNumChapter()));

                for (ParagraphModel paragraph : chapter.getParagraphs()) {
                    xmlStreamWriter.writeStartElement("paragraph");
                    xmlStreamWriter.writeAttribute("number", String.valueOf(paragraph.getNumParagraph()));

                    for (LineModel line : paragraph.getLines()) {
                        xmlStreamWriter.writeStartElement("line");
                        xmlStreamWriter.writeAttribute("number", String.valueOf(line.getLineNum()));
                        xmlStreamWriter.writeCharacters(line.getContent());
                        xmlStreamWriter.writeEndElement();
                    }

                    xmlStreamWriter.writeEndElement();
                }

                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement(); 

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();

            System.out.println("XML file created on staxObjToXML method");

        } catch (XMLStreamException | IOException e) {
            e.getMessage();
        }
    }

   

}
