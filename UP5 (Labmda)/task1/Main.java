package task1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    //(org)|(net)|(ru)|(com)|(by)
    final static Pattern EMAIL = Pattern.compile("[a-z]([._-]?[a-z]+)*[@][a-z]+([a-z]+[.]?)*[.][a-z]{2,}");
    final static Pattern N = Pattern.compile("([+]?[1-9]+)([0-9]*)");
    final static Pattern Z = Pattern.compile("[-+]?([0]|[1-9]+[0-9]*)");
    final static Pattern R = Pattern.compile("[-+]?(([0-9]*[.]?[0-9]+)|([0-9]+[.]?[0-9]*))([eE][-+]?[0-9]+)?");

    final static Pattern DATE = Pattern.compile("|(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))"//february
            + "|(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))"//31-day
            + "|(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))");//30-day
    final static Pattern TIME = Pattern.compile("(([0-1][0-9])|([2][0-3])):([0-5][0-9])");
    final static String[] ELEMENTS = {"E-Mail", "Натуральное число", "Целое число", "Вещественное число", "Дата", "Время"};

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableArrayList(ELEMENTS));
        comboBox.setValue("Натуральное число");
        primaryStage.setTitle("javaFX");
        TextField textField = new TextField();
        comboBox.setOnAction(event -> {
            if(comboBox.getValue().equals("Дата")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Date format");
                alert.setHeaderText("Date format is YYYY-MM-DD. Years from 1900 to 2999.");
                alert.show();
            }
        });
        FlowPane root = new FlowPane(10, 10, comboBox);
        Circle circle = new Circle(40, 100, 5);
        root.getChildren().add(textField);
        root.getChildren().add(circle);
        circle.setFill(Color.RED);

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m;
                switch (comboBox.getValue()) {
                    case "E-Mail":
                        m = EMAIL.matcher(newValue);
                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;

                    case "Натуральное число":
                        m = N.matcher(newValue);
                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;

                    case "Целое число":
                        m = Z.matcher(newValue);
                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;

                    case "Вещественное число":
                        m = R.matcher(newValue);
                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;

                    case "Дата":
                        m = DATE.matcher(newValue);

                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;

                    case "Время":
                        m = TIME.matcher(newValue);
                        if (m.matches())
                            circle.setFill(Color.GREEN);
                        else
                            circle.setFill(Color.RED);
                        break;
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
