package Service;

import Model.BookModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import Model.ChapterModel;
import Model.LineModel;
import Model.ParagraphModel;
import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class TxtToObject {

	public static void main(String[] args) {
		String txtFile = "txt/sample-lorem-ipsum-text-file.txt";
		BufferedReader txtReader = null;
		try {
			txtReader = new BufferedReader(new FileReader(txtFile));

		} catch (FileNotFoundException fnfe) {
			fnfe.getMessage();
		}
		List<ChapterModel> chapters = new ArrayList<>();
		List<ParagraphModel> paragraphs = new ArrayList<>();
		int lineNum = 0, paragraphCount = 0, lineCount = 0, wordCount = 0;
		int chapterNum = 1;
		String line, previousLine = "";
		try {
			while ((line = txtReader.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty() && previousLine.isEmpty()) {
					System.out.println("line");
					break;

				}
				if (line.isEmpty()) {
					previousLine = line;

				}

				lineNum++;
				if (line.endsWith(".")) {
					lineNum++;
					
				}

				ParagraphModel paragraphModel = new ParagraphModel(lineNum, List.of(new LineModel(lineNum, line)));
				paragraphs.add(paragraphModel);
				paragraphCount++;
				lineCount++;
				String[] wordsNum = line.split("\\s+");
				wordCount += wordsNum.length;

				if (lineNum % 20 == 0) {
					chapters.add((new ChapterModel(chapterNum, new ArrayList<>(paragraphs))));
					chapterNum++;
					paragraphs.clear();
				}
				previousLine = line;
			}
			if (!paragraphs.isEmpty()) {
				chapters.add((new ChapterModel(chapterNum, new ArrayList<>(paragraphs))));
			}
			txtReader.close();
			BookModel book = new BookModel(line, LocalDateTime.now(), chapters);
			book.setNumParagraphs(paragraphCount);
			book.setNumLines(lineNum);
			book.setWords(wordCount);
			book.setAuthor("Alexandros Aristeridis");

			System.out.println("Book object created");
			System.out.println("Author:" + book.getAuthor());
			System.out.println("Chapters:" + chapterNum);
			System.out.println("Paragraphs:" + book.getNumParagraphs());
			System.out.println("Lines:" + book.getNumLines());
			System.out.println("Words:" + book.getWords());
			System.out.println("Date"+LocalDateTime.now());

		} catch (IOException ioe) {
			ioe.getMessage();
		}

	}

}
