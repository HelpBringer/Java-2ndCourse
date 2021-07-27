package compositions;

public class OpenPoseWaltz  extends Waltz {
    String pose;

    public OpenPoseWaltz(String name, double duration, int size, MusicGenre genre) {
        super(name, duration,size,genre);
        if(genre==MusicGenre.OPEN_WALTZ) {
            this.pose = "open";
        }
        else{
            System.out.println("Trying to create wrong posed Waltz");
        }
    }

}
