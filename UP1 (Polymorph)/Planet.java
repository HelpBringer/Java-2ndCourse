import java.util.ArrayList;
import java.util.Collections;

public class Planet extends Area {
    ArrayList<Ocean> oceanCol;
    ArrayList<Continent> continentCol;
    Planet(String name, ArrayList<Ocean> oceanCol, ArrayList<Continent> continentCol){
        super(name, 0,0);
        this.continentCol=continentCol;
        this.oceanCol=oceanCol;
        population=countPopulation();
        square=countSquare();
    }
    Planet(){
        super();
        oceanCol=null;
        continentCol=null;
    }
    public String toString(){
        StringBuffer tmpString= new StringBuffer(this.name+" contains: ");
        for (Ocean tmp : oceanCol) {
            tmpString.append(tmp.toString()).append(" ");
        }
        tmpString.append("and: ");
        for (Continent tmp : continentCol) {
            tmpString.append(tmp.toString()).append(" ");
        }
        return new String(tmpString);
    }

    Planet(String name, double square, int population) {
        super(name, square, population);
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

        Planet that = (Planet) obj;

        if (population != that.population) return false;
        if (!name.equals(that.name)) return false;
        if (square != that.square) return false;
        Collections.sort(this.continentCol);
        Collections.sort(that.continentCol);
        Collections.sort(this.oceanCol);
        Collections.sort(that.oceanCol);
        return (continentCol.equals(that.continentCol) && oceanCol.equals(that.oceanCol));
    }

    @Override
    public int countPopulation() {
        int tmpPop=0;
        for (Ocean tmp : oceanCol) {
            tmpPop+=tmp.countPopulation();
        }
        for (Continent tmp : continentCol) {
            tmpPop+=tmp.countPopulation();
        }
        return tmpPop;
    }

    @Override
    public double countSquare() {
        int tmpSqr=0;
        for (Ocean tmp : oceanCol) {
            tmpSqr+=tmp.countSquare();
        }
        for (Continent tmp : continentCol) {
            tmpSqr+=tmp.countSquare();
        }
        return tmpSqr;
    }
    public static void main(String[] args){
        final String islandName="StaticIsland";
        final String oceanName="StaticOcean";
        final String continentName="StaticContinent";
        final String planetName="StaticPlanet";
        ArrayList<Island> islands1 = new ArrayList<>();
        ArrayList<Island> islands2 = new ArrayList<>();
        ArrayList<Island> islands3 = new ArrayList<>();
        ArrayList<Ocean> oceans1= new ArrayList<>();
        ArrayList<Ocean> oceans2= new ArrayList<>();
        ArrayList<Continent> continents1= new ArrayList<>();
        ArrayList<Continent> continents2= new ArrayList<>();
        for(int i=0;i<5;i++){
            Island tmp =new Island(islandName,i,i*i);
            islands1.add(tmp);
            tmp =new Island(islandName,i*i, i);
            islands2.add(tmp);
            tmp =new Island(islandName,i*i, i*i);
            islands3.add(tmp);
        }
        for(int i=5;i<8;i++){
           Continent tmp =new Continent(continentName,i,i*i);
           continents1.add(tmp);
           tmp =new Continent(continentName,i*i,i);
           continents2.add(tmp);
        }
        Ocean ocean1=new Ocean(oceanName,27,islands1);
        Ocean ocean2=new Ocean(oceanName,34,islands2);
        Ocean ocean3=new Ocean(oceanName,41,islands3);
        oceans1.add(ocean1);
        oceans1.add(ocean2);
        oceans2.add(ocean2);
        oceans2.add(ocean3);

        Planet planet1=new Planet(planetName,oceans1,continents1);
        Planet planet2=new Planet(planetName,oceans2,continents2);

        System.out.println(planet1.equals(planet1));
        System.out.println(planet1.hashCode());
        System.out.println(planet1.hashCode());
        System.out.println(planet2.hashCode());
        System.out.println(planet1.toString());
        System.out.println(planet2.toString());
        System.out.println(planet1.equals(planet2));

    }
}
