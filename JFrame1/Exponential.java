public class Exponential extends Series {
    Exponential(double firstEl, int count, double coef){
        super(firstEl, count, coef);
    }
    public double findJ(int j){
        return firstEl*Math.pow(coef,j);
    }
    public double findSum() {
        if (Math.abs(coef) > 1) {
            return firstEl * (1 - Math.pow(coef, count)) / (1 - coef);
        } else {
            if (coef==1){ return firstEl*count;}
            return firstEl/(1-coef);
        }
    }

}
