package Model;

import java.time.LocalDateTime;
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

	public BookModel(String author, LocalDateTime creationDate, List<ChapterModel> chapters) {
		this.author = author;
		this.creationDate = creationDate;
		this.chapters = chapters;
	}
	private String author;
	private LocalDateTime creationDate;
	private List<ChapterModel> chapters;
	private int numParagraphs;
	private int numLines;
	private int words;
	private int distinctWords;
	
	

}
