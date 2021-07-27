public class Continent extends Area implements Comparable {

    public Continent(String name, double square, int population) {
        super(name, square, population);
    }

    public Continent() {
        super();
    }

    @Override
    public String toString() {
        String out= new String(name+" "+square+" "+population);
        return out;
    }

    @Override
    public double countSquare() {
        return square;
    }

    @Override
    public int compareTo(Object obj) {

        if (this == obj) return 0;
        Continent that = (Continent) obj;

        if (population > that.population) return 1;
        if (population < that.population) return -1;
        return Double.compare(square, that.square);
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

        Continent that = (Continent) obj;

        if (population != that.population) return false;
        if (!name.equals(that.name)) return false;
        return square == that.square;
    }

    @Override
    public int countPopulation() {
        return population;
    }
}
