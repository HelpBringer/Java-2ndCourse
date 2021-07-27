import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class SolveSystem {
    static double[][] mat;
    static double[] result;
    static int n;

    static  void readMatrix(String s) throws IllegalArgumentException, IOException, NoSuchElementException, IndexOutOfBoundsException {
        Path path = Paths.get(s);
        Scanner scan= new Scanner(path);
        n=scan.nextInt();
        if (n<2){
            throw new IllegalArgumentException("n<2");
        }
        mat= new double[n][];
        result = new double[n];
        for (int i=0;i<n;i++){
            mat[i]=new double[n-i+1];
        }
        for (int i=0;i<n;i++){
            for(int j=0;j<n-i+1;j++){
                if (!scan.hasNext()){
                    throw new NoSuchElementException("not enough numbers");
                }
                mat[i][j]=scan.nextInt();
            }
        }
        if (scan.hasNext()){
            throw new IndexOutOfBoundsException("too much numbers");
        }
        //return mat;
    }
    static void solve(){
        double sum=0.0;
        double lastInRow=1;
        double firstInRow=1;
        for (int i=n-1;i>-1;i--){
         sum=0.0;
            firstInRow=mat[i][0];
            if( firstInRow==0){
                throw new IllegalArgumentException("at least one 0 on main diagonal");
            }
            lastInRow=mat[i][n-i];
         for (int j=1;j<n-i;j++){

                     sum+=mat[i][j]*result[i+j];

         }
         result[i]=(lastInRow -sum) / firstInRow ;
        }
    }

    public static void main(String[] args)  {
        try {
            readMatrix(args[0]);
        }
        catch( IndexOutOfBoundsException | IllegalArgumentException   e){
            System.out.print (e.getMessage());
            System.exit(0);
        }
        catch( NoSuchElementException e){
            System.out.print ("Characters aren't allowed");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.print("File not found");
            System.exit(0);
        }
        try {
            solve();
        }
        catch(IllegalArgumentException e){
            System.out.print (e.getMessage());
            System.exit(0);
        }
        //For debug purposes only:
        for (int i = 0; i <n /*n*/ ; i++) {
            for (int j = 0; j <n+1 -i /*n+1-i*/; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i=0;i<n;i++){
            System.out.println(result[i]+" ");
        }
    }
}
