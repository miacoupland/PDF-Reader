import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Label book, pageCount;
    @FXML private TextField fileNameField, skipToPage;
    @FXML private Button readBtn, pageBack, pageForward, skip;
    private Reader reader;
    private int startPage, endPage;


    public Controller() throws IOException {
        this.reader = new Reader();
        this.startPage = 1; //start at first page of book
        this.endPage = 1; //page numbers are inclusive
    }

    @FXML public void setText(String text) {
        book.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText("READING");
    }

    public int getStartPage() {return startPage;}

    @FXML public void readBtn(Event e) throws IOException {
        read();
    }

    @FXML public void pageBack(Event e) throws IOException {
        if (startPage > 1) {
            startPage--;
            endPage--;
        } else {
            System.out.println("Can't go back any further!");
        }
        read();
    }

    @FXML public void pageForward(Event e) throws IOException {
        startPage++;
        endPage++;
        read();
    }

    @FXML public void skip(Event e) throws IOException {
        startPage = Integer.valueOf(skipToPage.getText());
        endPage = startPage + 1;
        read();
    }

    @FXML public void read() throws IOException {
        try {
            setText(reader.read( new File(fileNameField.getText()), startPage, endPage));
            pageCount.setText("Page: " + getStartPage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
