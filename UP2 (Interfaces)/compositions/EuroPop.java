package compositions;

public class EuroPop extends Pop {
    String region;

    public EuroPop(String name, double duration, int size, MusicGenre genre) {
        super(name, duration, size, genre);
        if(genre==MusicGenre.EURO_POP) {
            this.region = "Europe";
        }
        else{
            System.out.println("Trying to create wrong EuroPop");
        }
    }
}
