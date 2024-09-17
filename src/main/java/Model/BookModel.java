package Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "book")
@XmlType(propOrder = {"author", "chapters", "distinctWords", "numParagraphs", "numLines", "words"})

public class BookModel {

	public BookModel(String author, Date creationDate, List<ChapterModel> chapters) {
		this.author = author;
		//	this.creationDate = creationDate;
		this.chapters = chapters;
	}
	private String author;
	private List<ChapterModel> chapters;
	private int distinctWords;
	private int numLines;
	//private Date creationDate;
	private int numParagraphs;
	private int words;
}
