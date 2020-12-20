import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

public class Controller {
    private final TextField fileNameField, skipToPage;
    private final Label book, pageCount;
    private final Reader reader;
    private int startPage, endPage;
    private final Button readBtn, pageForward, pageBack, skip;

    public Controller() throws IOException {
        reader = new Reader();
        book = new Label("Your book will go here!");
        fileNameField = new TextField();
        startPage = 1; //start at first page
        endPage = 1; //page numbers are inclusive
        readBtn = new Button("Read File");
        pageForward = new Button("Page forward");
        pageBack = new Button("Page back");
        pageCount = new Label("Page: " + startPage);
        skipToPage = new TextField();
        skip = new Button("Skip to page");
    }

    public void editLabel(String text) {book.setText(text);}

    public Scene addComponents() {
        GridPane pane = new GridPane();
        HBox hbox = new HBox();
        ScrollPane sp = new ScrollPane();

        readBtn.setOnAction((event) -> {
            read();
        });

        pageForward.setOnAction((event) -> {
            startPage++;
            endPage++;
            read();
        });

        pageBack.setOnAction((event) -> {
            if (startPage > 1) {
                startPage--;
                endPage--;
                read();
            } else {
                System.out.println("Can't go back any further!");
            }
        });

        skip.setOnAction((event) -> {
           startPage = Integer.valueOf(skipToPage.getText());
           endPage = startPage + 1;
           read();
        });

        hbox.getChildren().addAll(fileNameField, readBtn, pageBack,
                pageForward, pageCount, skipToPage, skip);
        hbox.setSpacing(10);
        pane.add(hbox, 0, 0);
        sp.setContent(book);
        sp.setVmax(400);
        sp.setPrefSize(500, 400);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.add(sp, 0, 1);
        pane.setAlignment(Pos.CENTER);

        pane.setHgap(10);
        pane.setVgap(10);

        Scene scene = new Scene(pane, 800, 500);

        return scene;
    }

    private void read() {
        try {
            editLabel(reader.read(new File(fileNameField.getText()), startPage, endPage));
            pageCount.setText("Page: " + startPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
