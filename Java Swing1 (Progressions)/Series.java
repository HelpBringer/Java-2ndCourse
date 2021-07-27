import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    double firstEl;
    int count;
    double coef;

    protected Series(double firstEl, int count, double coef) throws IllegalArgumentException{
        if (count<0){
            throw new IllegalArgumentException("count value<0");
        }
        this.firstEl = firstEl;
        this.count = count;
        this.coef = coef;
    }

    protected abstract double findJ(int j);

    public double findSum() {
        double sum = 0;
        for (int i = 0; i < this.count; i++) {
            sum += findJ(i);
        }
        return sum;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append(findJ(i)).append(" ");
        }
        return str.toString();
    }

    public void fOut(String path) throws IOException {
        FileWriter fOutFile = new FileWriter(path);
        fOutFile.write(toString());
        fOutFile.close();
    }


}



