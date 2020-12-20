import java.io.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Reader {
    File textFile;
    FileWriter writer;

    public Reader() throws IOException {
        textFile = new File("src/output.txt");
        writer = new FileWriter(textFile, true);
    }

    //code tutorial from https://www.tutorialspoint.com/how-to-read-data-from-pdf-file-and-display-on-console-in-java
    public String read(File file, int startPage, int endPage) throws IOException {
        PDDocument document = PDDocument.load(file);
        //instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(startPage);
        pdfStripper.setEndPage(endPage);

        //prints to output file so you know what page you were on
        writer.write("Read up to page " + startPage);
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }
}
