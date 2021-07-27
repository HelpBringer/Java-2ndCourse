public class First {
    public static void main(String[] s)  {
        try {
            if (s.length != 2) {
                throw new LengthException("length !=2");
            }
            System.out.println("Sum is" + findSum(Double.parseDouble(s[0]), Double.parseDouble(s[1])));
        } catch (LengthException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Incorect number");
        }
    }
    static class LengthException extends Exception{
        public LengthException(String str){
            super(str);
        }
    }
    static double findSum(double x, double e){
        double sum=0;
        double k=1;
        double addendum=(-1)*x/((k+1)*(k+1));
        while (Math.abs(addendum)>e){
            sum+=addendum;
            k++;
            addendum*=(-1)*x*Math.pow(k/(k+1),2);
        }
        return sum;
    }
}