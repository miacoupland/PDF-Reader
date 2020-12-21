import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {
    File textFile;
    FileWriter writer;

    public Reader() throws IOException {

    }

    //code tutorial from https://www.tutorialspoint.com/how-to-read-data-from-pdf-file-and-display-on-console-in-java
    public String read(File file, int startPage, int endPage) throws IOException {
        PDDocument document = PDDocument.load(file);
        //instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //select which part of PDF to read
        pdfStripper.setStartPage(startPage);
        pdfStripper.setEndPage(endPage);

        //retrieve text to be returned
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }
}
