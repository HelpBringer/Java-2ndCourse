package user;

import compositions.Composition;
import tools.ContainerInterface;

import java.util.ArrayList;

public class User {
    private ContainerInterface container;

    public User(ContainerInterface container) {
        this.container = container;
    }

    public void setContainer(ContainerInterface container) {
        this.container = container;
    }

    public void sort(){
        container.sort();
    }

    public int countMemorySize(){
        return container.countMemorySize();
    }

    public double countDuration(){
        return  container.countDuration();
    }
    public ArrayList<Composition> findByDuration(double min, double max){
        return container.findByDuration(min, max);
    }
    public String toString(){
        return container.toString();
    }
}