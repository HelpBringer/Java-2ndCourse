import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DepthSearch {
    private static int[] result;
    private static ArrayList<Integer>[] adj;
    private static int counter;
    private static boolean[] visit;
    private static void dfs( Integer top){
        visit[top] = true;
        result[top] = ++counter;
        for( Integer adjTop: adj[top]){
            if(!visit[adjTop]){
                dfs(adjTop);
            }
        }
    }
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            result = new int [Integer.parseInt(sc.next())];
            adj = new ArrayList[result.length];
            for( int i = 0; i < result.length; i++){
                adj[i] = new ArrayList<>();
                for( int j = 0; j < result.length; j++){
                    if( Integer.parseInt(sc.next()) == 1){
                        adj[i].add(j);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        counter = 0;
        visit = new boolean[result.length];
        for( int i = 0; i < result.length; i++){
            if(!visit[i]){
                dfs(i);
            }
        }
        try (FileWriter fw = new FileWriter("output.txt")) {
            for (int aResult : result) {
                fw.write(Integer.toString(aResult));
                fw.write(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }
}


