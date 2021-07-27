
abstract class Area {
    protected String name;
    protected double square;
    protected int population;
    Area(String name, double square, int population) {
        this.name=name;
        this.population=population;
        this.square=square;
    }
    Area(){
        this.name="";
        this.population=-1;
        this.square=-1;
    }
    abstract public String toString();
    abstract public int countPopulation();
    abstract public double countSquare();
    abstract public int hashCode();
    abstract public boolean equals(Object obj);
}
