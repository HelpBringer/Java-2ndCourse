package sample;

import javafx.scene.text.Text;
//Log (Subscriber)
public class Log implements Observer {
    private Text text;
    private TextLogger logger;

    public Log(TextLogger textLogger, Text text) {
        this.logger = textLogger;
        this.text = text;
        logger.registerObserver(this);
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void update(String s) {
        text.setText(text.getText()+s);
    }
}
