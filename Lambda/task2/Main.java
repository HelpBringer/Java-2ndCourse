package task2;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    final static Pattern DATE = Pattern.compile("(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))"//february
            + "|(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))"//31-day
            + "|(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))");//30-day
    final static String START_STRING = "ывыфавщл-04-0-ыв2000-20-20\n" +
            "авываываыв03к0499-2000-02-28ываыва\n" +
            "ываываывавыаыdаыв20ывекеdsdfsdаыв2ываы2аываы2\n" +
            "2000-02-2ываывdfsdаы1958-02-29ываыв1958-02-28\n" +
            "ываыаыаfdfsfsdываываыв2012-13-25ываыв2016-12-35ыва\n" +
            "ываываываывfdsf sdf kh.!@#$%^&*()_авыавыаыв2016-11-30ываываы\n";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane(10, 10);
        root.autosize();

        TextArea textArea = new TextArea();
        ListView<String> list=new ListView<String>();

        textArea.setPrefRowCount(6);
        root.getChildren().add(textArea);
        textArea.setText(START_STRING);
        Button findDates = new Button("find dates!");
        root.getChildren().add(findDates);
        root.getChildren().add(list);
        primaryStage.setTitle("Search");
        findDates.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList<String> obs = FXCollections.observableArrayList();
                String text = textArea.getText();
                Matcher matcher = DATE.matcher(text);
                while (matcher.find()) {
                    obs.add(matcher.group());
                    list.setItems(obs);
                }
            }
        });
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
