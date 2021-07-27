package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
//Application
public class Main extends Application {

    Text text1 = new Text();
    Text log = new Text();

    @Override
    public void start(Stage primaryStage) {

        text1.setFont(Font.font("Serif" , FontWeight.BOLD, FontPosture.ITALIC, 40));

        BorderPane bp = new BorderPane();

        bp.setLeft(text1);
        bp.setBottom(log);

        //Observable -> Observers
        TextLogger logger = new TextLogger();

        Log log = new Log(logger, this.log);
        LastKey lastKey = new LastKey(logger, text1);

        Scene scene = new Scene(bp, 1000, 400);
        scene.getRoot().requestFocus();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getRoot().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String s = e.getCode().getName();
                logger.setText(s);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}