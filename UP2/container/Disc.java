package container;

import compositions.Composition;
import compositions.MusicGenre;

import java.util.ArrayList;
import java.util.Collections;

public class Disc implements tools.ContainerInterface  {

    private ArrayList<Composition> compositions;
    private int capacity;

    public Disc(ArrayList<Composition> compositions, int capacity){
        setCapacity(capacity);
        setCompositions(compositions);

    }

    public ArrayList<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(ArrayList<Composition> compositions) {
        int sum=0;
        for (Composition i: compositions){
            sum+=i.getMemorySize();
        }
        if (sum>this.getCapacity()){
            System.out.println("Capacity of your disc is not enough for your compositions");
        }
        else {
            this.compositions = compositions;
        }

    }

    private void addComposition(Composition composition){
        if (this.countMemorySize()+composition.getDuration()>this.getCapacity()){
            System.out.println("Capacity of your disc is not enough for your composition");
        }
        else {
            compositions.add(composition);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity<=0){
            System.out.println("Capacity of your disc can't be less or equal to 0");
        }
        else {
            this.capacity = capacity;
        }
    }

    @Override
    public double countDuration() {
        if(compositions==null){
            return 0;
        }
        int sum=0;
        for (Composition i: compositions){
            sum+=i.getDuration();
        }
        return sum;
    }

    @Override
    public int countMemorySize() {
        if(compositions==null){
            return 0;
        }
        int sum=0;
        for (Composition i: compositions){
            sum+=i.getMemorySize();
        }
        return sum;
    }

    @Override
    public void sort() {
        if(compositions==null){
            return ;
        }
        Collections.sort(compositions);
    }


    public String toString() {
        if(compositions==null){
            return "Your disc is empty";
        }
        StringBuffer tmpString= new StringBuffer("Disc contains: ");
        for (Composition tmp : compositions) {
            tmpString.append(tmp.toString()).append(" ");
        }
        return new String(tmpString);
    }

    @Override
    public ArrayList<Composition> findByDuration(double min, double max) {
        ArrayList<Composition> result=new ArrayList<>();
        if(compositions==null){
            result.add(new Composition("Your disc is empty",0.0,0, MusicGenre.ERROR));
            return result;
        }

        for(Composition i:compositions){
            if(i.getDuration()>=min && i.getDuration()<=max){
                result.add(i);
            }
        }
        if(result.size()==0){
          result.add(new Composition("No compositions found",0.0,0, MusicGenre.ERROR));
        }
        return result;
    }
}
