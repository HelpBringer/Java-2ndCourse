package compositions;

public enum MusicGenre {

    //CLASSIC(1),
    EURO_POP(2),
    OPEN_WALTZ(3),
    /*ALTERNATIVE(4),
    METAL(5),
    PUNK(6),
    ROCK(7),
    OPERA(8),*/
    ERROR(9),
    CLOSED_WALTZ(10),
    ASIAN_POP(11);
    private int value;
    MusicGenre(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
