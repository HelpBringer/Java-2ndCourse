import javax.swing.*;

public class Country implements Comparable<Country>{
    private String name;
    private String capital;
    private ImageIcon flag;

    Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
        this.flag = getImage();
    }

    public Country(String name) {
        this.name = name;
        this.flag = getImage();
    }

    public int compareTo(Country o)
    {
        return this.name.compareTo(o.getName());
    }


    private ImageIcon getImage() {
        String str = this.name;
        str = str.toLowerCase();
        return new ImageIcon("src\\plain\\flag_" + str + ".png");
    }

    Icon getFlagIcon() {
        return this.flag;
    }

    String getName() {
        return this.name;
    }

    String getCapital() {return this.capital;}


}
