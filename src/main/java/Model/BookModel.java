package Model;

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

public class BookModel {

	public BookModel(String author, Date creationDate, List<ChapterModel> numChapters) {
		this.author = author;
		this.creationDate = creationDate;
		this.numChapters = numChapters;
	}
	private String author;
	private Date creationDate;
	private List<ChapterModel> numChapters;
	private int numParagraphs;
	private int numLines;
	private int words;
	private int distinctWords;
	
	

}
