package compositions;

//import static compositions.MusicGenre.WALTZ;

abstract public class Waltz extends Composition{

    public Waltz() {
    }

    Waltz(String name, double duration, int size, MusicGenre genre){
        super();
            this.setDuration(duration);
            this.setGenre(genre);
            this.setMemorySize(size);
            this.setName(name);
    }




}
