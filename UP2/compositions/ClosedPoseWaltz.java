package compositions;

public class ClosedPoseWaltz extends Waltz {
    String pose;

    public ClosedPoseWaltz(String name, double duration, int size, MusicGenre genre) {
        super(name, duration,size,genre);
        if(genre==MusicGenre.CLOSED_WALTZ) {
            this.pose = "closed";
        }
        else{
            System.out.println("Trying to create wrong posed Waltz");
        }
    }
}
