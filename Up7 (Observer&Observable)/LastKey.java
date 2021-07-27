package sample;

import javafx.scene.text.Text;
//Last key (Subscriber)
public class LastKey implements Observer {
    private TextLogger logger;
    private Text text;

    public LastKey(TextLogger logger, Text text) {
        this.logger = logger;
        this.text = text;
        logger.registerObserver(this);
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void update(String s) {
        text.setText("Last key:"+s);
    }
}