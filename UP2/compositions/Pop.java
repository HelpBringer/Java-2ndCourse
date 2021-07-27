package compositions;

//import static compositions.MusicGenre.POP;

abstract public class Pop extends Composition{
    public Pop() {
    }

    Pop(String name, double duration, int size, MusicGenre genre){
        super();

           this.setDuration(duration);
           this.setGenre(genre);
           this.setMemorySize(size);
           this.setName(name);

    }






}
