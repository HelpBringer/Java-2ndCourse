public class Liner extends Series{
    Liner(double firstEl, int count, double coef){
        super(firstEl, count, coef);
    }
    public double findJ(int j){
        return firstEl + coef*(j);
    }
    public double findSum(){
        return ((firstEl+findJ(count-1))/2)*count;
    }
}
