import java.text.SimpleDateFormat;
import java.util.*;

public class MyCell extends GregorianCalendar {
    private String currentFormula;
    private HashSet<int[]> usingList;

    public MyCell(MyCell date) {
        super(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        this.currentFormula = date.getCurrentFormula();
        this.usingList = new HashSet<>();
    }

    public MyCell(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
        this.currentFormula = getFormula(toString());
        this.usingList = new HashSet<>();
    }

    public void setUsingDependencies(HashSet<int[]> set)
    {
        if (set!=null)
        this.usingList = set;
    }


    public String getFormula(String s) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        fmt.setCalendar(this);
        return fmt.format(this.getTime());
    }

    public void addUsingDependency(int i, int j) {
        int[] array = new int[2];
        array[0] = i;
        array[1] = j;
        this.usingList.add(array);
    }

    public HashSet getUsingDependencies() {
        return usingList;
    }

    public int getAmountOfDependencies() {
        return usingList.size();
    }

    public String getCurrentFormula() {
        return currentFormula;
    }

    public void setCurrentFormula(String currentFormula) {
        this.currentFormula = currentFormula;
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        fmt.setCalendar(this);
        return fmt.format(this.getTime());
    }
}
