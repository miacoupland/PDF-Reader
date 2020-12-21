import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    File textFile;

    public Reader() throws IOException {
        textFile = new File("src/bookmark.txt");
    }

    //code tutorial from https://www.tutorialspoint.com/how-to-read-data-from-pdf-file-and-display-on-console-in-java
    public String read(File file, int startPage, int endPage) throws IOException {
        PDDocument document = PDDocument.load(file);
        //instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //select which part of PDF to read
        pdfStripper.setStartPage(startPage);
        pdfStripper.setEndPage(endPage);
        //writes to the bookmark file to remind the user which page they got up to
        FileWriter writer = new FileWriter(textFile);
        writer.write("You have read up to page " + startPage);
        writer.close();

        //retrieve text to be returned
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }
}
