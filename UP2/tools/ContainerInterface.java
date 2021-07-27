package tools;

import compositions.Composition;

import java.util.ArrayList;

public interface ContainerInterface {
    double countDuration();
    int countMemorySize();
    ArrayList<Composition> findByDuration(double min, double max);
    void sort();
}
