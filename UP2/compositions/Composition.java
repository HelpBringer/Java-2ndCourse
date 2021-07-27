package compositions;

public class Composition implements Comparable {
    private  String name;
    private double duration;
    private int memorySize;
    private MusicGenre genre;

    public Composition() {
    }

    public Composition(String name, double duration, int memorySize, MusicGenre genre) {
        this.name = name;
        setDuration(duration);
        setMemorySize(memorySize);
        this.genre = genre;
    }

    @Override
    public String toString() {
        return new String("Name of file is: "+getName()+". It's duration is: "+getDuration()
                                 +". File size is: "+getMemorySize()+". It's genre is: "+getGenre()+".\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if(duration <= 0){
            System.out.println("Input duration can't be less or equal to 0");
        }
        else {
            this.duration = duration;
        }

    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        if( memorySize <= 0){
            System.out.println("Input memory size can't be less or equal to 0");
        }
        else {
            this.memorySize = memorySize;
        }
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    @Override
    public int compareTo(Object obj) {
        if (this == obj) return 0;
        Composition that = (Composition) obj;

        if(getGenre()!=that.getGenre()) {
            if (getGenre().getValue() > that.getGenre().getValue()) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if(getDuration()!=that.getDuration()) {
            if (getDuration() > that.getDuration()){
                return 1;
            }
            else{
                return -1;
            }
        }
        if(!getName().equals(that.getName())) {
            if (getName().length() > that.getName().length()){
                return 1;
            }
            else{
                return -1;
            }
        }
        return (Integer.compare(getMemorySize(),that.getMemorySize()));

    }
}
