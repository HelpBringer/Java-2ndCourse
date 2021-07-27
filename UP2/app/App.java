package app;

import compositions.Composition;
import compositions.MusicGenre;
import container.Disc;
import user.User;

import java.util.ArrayList;

public class App {
    public static void main(String[] args){

        ArrayList<Composition> music=new ArrayList<>();
        music.add(new Composition("Toxicity", 3.01, 21, MusicGenre.ASIAN_POP));
        music.add(new Composition("Waste of time",2.33,22, MusicGenre.ASIAN_POP));
        music.add(new Composition("long story short end", 2.11, 12, MusicGenre.EURO_POP));
        music.add(new Composition("The Flight of the Valkyries", 3.28, 34, MusicGenre.EURO_POP));
        music.add(new Composition("King and jester. Concert", 101.22, 700, MusicGenre.CLOSED_WALTZ));
        music.add(new Composition("daisies", 2.11, 12, MusicGenre.OPEN_WALTZ));
        music.add(new Composition("smth", 2.55, 19, MusicGenre.OPEN_WALTZ));
        //music.add(new Composition("Error", 1111.1,11111,MusicGenre.ERROR));
        int tmp=1024;
        Disc example= new Disc(music,tmp);
        User user=new User(example);
        System.out.println(user.countMemorySize());
        System.out.println(user.countDuration());
        System.out.println(user.findByDuration(2.3,3.01));
        System.out.println(user.toString());
        user.sort();
        System.out.println(user.toString());

       /* System.out.println();
        System.out.println();
        System.out.println(example.countMemorySize());
        System.out.println(example.countDuration());
        System.out.println(example.findByDuration(2.0,3.01));
        System.out.println();
        System.out.println(example);
        example.sort();
        System.out.println(example);*/
    }
}
