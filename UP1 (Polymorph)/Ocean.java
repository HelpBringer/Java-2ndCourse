import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ocean extends Area implements Comparable {
    Ocean(){
        super();
        islandCol=null;
    }
    Ocean(String name, double square, ArrayList<Island> islandCol){
        super(name, square, 0);
        this.islandCol=islandCol;
        this.population=countPopulation();
    }
    ArrayList<Island> islandCol;

    public String toString(){
        StringBuffer tmpString= new StringBuffer("Islands in "+this.name+" are: ");
        for (Island tmp : islandCol) {
            tmpString.append(tmp.toString()).append(" ");
        }
        String out = new String(tmpString);
        return out;
    }

    @Override
    public int hashCode() {
        final int c=31;
        int result= name.hashCode();
        result=result*c+ population;
        result= result*c + (int)square;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Ocean that = (Ocean) obj;

        if (population != that.population) return false;
        if (!name.equals(that.name)) return false;
        if (square != that.square) return false;
        Collections.sort(this.islandCol);
        Collections.sort(that.islandCol);
        return islandCol.equals(that.islandCol);
    }

    @Override
    public int countPopulation() {
        int nPopulation=0;
        for (Island tmp : islandCol) {
         nPopulation+=tmp.countPopulation();
        }
        return nPopulation;
    }

    @Override
    public double countSquare() {
        return square;
    }

    @Override
    public int compareTo(Object obj) {
        if (this == obj) return 0;
        Ocean that = (Ocean) obj;

        if (population > that.population) return 1;
        if (population < that.population) return -1;
        //Supposed that different array of islands provide different population & square
        return Double.compare(square, that.square);
    }
}
