package sample;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
//EventManger (Publisher)
public class TextLogger implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private Text text;
    private String newText;

    @Override
    public void registerObserver(Observer o) {
        if (o != null) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        if (obs != null) {
            observers.remove(obs);
        }
    }

    @Override
    public void notifyObservers() {
        for (var elem : observers) {
            elem.update(newText);
        }
    }

    public void setText(String s){
        newText = s;
        notifyObservers();
    }

}
