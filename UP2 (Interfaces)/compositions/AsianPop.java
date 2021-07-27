package compositions;

public class AsianPop extends Pop {
    String region;

    public AsianPop(String name, double duration, int size, MusicGenre genre) {
        super(name, duration, size, genre);
        if(genre==MusicGenre.ASIAN_POP) {
            this.region = "Asian";
        }
        else{
            System.out.println("Trying to create wrong AsianPop");
        }
    }
}
