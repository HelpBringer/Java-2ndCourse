
import javax.swing.*;
import java.awt.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.TableCellEditor;

class CellController extends AbstractCellEditor implements TableCellEditor {
    final static public String MESSAGE = "Давайте писать по новой. Правильно будет: \"=МИН\" или \"=МАКС\"(yyyy-mm-dd,A4,B2), или \"=yyyy-mm-dd +- int\" или \"=CELL +- int\"!";
    final public static WrongFormulaFormatException EXCEPTION = new WrongFormulaFormatException(MESSAGE);

    final public static char M = 'М';
    final public static char SIGN_OF_EQUALITY = '=';
    final static Pattern DATE = Pattern.compile("((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)"
            + "|(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))"
            + "|(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))"
            + "|(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))");
    final static Pattern CELL = Pattern.compile("[A-Z][1-9]+[0-9]*");

    private JTextField editor;
    private MyCell currentValue;
    private JTable table;
    int curRow, curCol;
    boolean notifier = false;
    boolean needToNotify = false;



    public CellController() {
        super();
        editor = new JTextField();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = (MyCell) value;
        this.table = table;
        curCol = column;
        curRow = row;

        if (currentValue != null) {
            editor.setText(currentValue.getCurrentFormula());
            needToNotify = true;
        } else {
            editor.setText("");
            needToNotify = false;
        }
        return this.editor;
    }

    public Object getCellEditorValue() {
        MyCell date = currentValue;
        try {
            date = parseDate(editor.getText());
            if (currentValue != null)
                date.setUsingDependencies(currentValue.getUsingDependencies());
            currentValue = date;
            table.getModel().setValueAt(date, curRow, curCol);
            if (needToNotify)
                notifyTable();
        } catch (
                WrongFormulaFormatException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        } catch (
                NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, MESSAGE);
        }

        return date;
    }

    private MyCell parseMaxOrMin(String str) throws WrongFormulaFormatException, NumberFormatException {
        ArrayList<MyCell> container = new ArrayList<>();
        boolean isMin;
        if (str.charAt(2) == 'И' && str.charAt(3) == 'Н') {
            isMin = true;
        } else if (str.charAt(2) == 'А' && str.charAt(3) == 'К' && str.charAt(4) == 'С') {
            isMin = false;
        } else throw EXCEPTION;
        String cells = "";
        if (isMin) {
            if (str.charAt(4) != '(') throw EXCEPTION;
        } else {
            if (str.charAt(5) != '(') throw EXCEPTION;
        }
        Matcher m = DATE.matcher(str);
        while (m.find()) {
            cells = m.group();
            container.add(parseFullDate(cells));
        }
        cells = str.substring(str.indexOf(cells) + cells.length());
        m = CELL.matcher(cells);
        while (m.find()) {
            MyCell myCell = parseCellDate(m.group());
            if (myCell.equals(null)) throw EXCEPTION;
            container.add(myCell);
        }
        if (cells.charAt(cells.length() - 1) != ')') throw EXCEPTION;

        if (isMin) return Collections.min(container);
        else return Collections.max(container);
    }

    private MyCell parseEqualFormula(String str) throws WrongFormulaFormatException, NumberFormatException {
        MyCell first;
        int second;
        boolean isMinus;
        if (str.charAt(1) >= '1' && str.charAt(1) <= '9') {
            String usualDate = str.substring(1, 11);
            Matcher m = DATE.matcher(usualDate);
            if (m.matches()) {
                first = parseFullDate(usualDate);
                second = Integer.parseInt(str.substring(12));
                if (str.charAt(11) == '+')
                    isMinus = false;
                else if (str.charAt(11) == '-')
                    isMinus = true;
                else throw EXCEPTION;
                if (isMinus)
                    second *= -1;
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else throw EXCEPTION;
        } else if (str.charAt(1) >= TableView.A && str.charAt(1) <= 'Z') {
            int ind = str.indexOf('-');
            isMinus = true;
            if (ind == -1) {
                ind = str.indexOf('+');
                isMinus = false;
            }
            if (ind == -1) throw EXCEPTION;
            String cell = str.substring(1, ind);
            Matcher m = CELL.matcher(cell);
            if (m.matches()) {
                first = parseCellDate(cell);
                if (first.equals(null)) throw EXCEPTION;
                second = Integer.parseInt(str.substring(cell.length() + 2));
                if (isMinus)
                    second *= -1;
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else throw EXCEPTION;
        } else throw EXCEPTION;
    }

    private MyCell parseFullDate(String str) throws NumberFormatException {
        String day, month, year;
        int dayI, monthI, yearI;
        day = str.substring(8, 10);
        month = str.substring(5, 7);
        year = str.substring(0, 4);
        dayI = Integer.parseInt(day);
        monthI = Integer.parseInt(month);
        yearI = Integer.parseInt(year);
        return new MyCell(yearI, monthI - 1, dayI);
    }

    private MyCell parseUsualDate(String str) throws NumberFormatException, WrongFormulaFormatException {
        Matcher m = DATE.matcher(str);
        if (m.matches()) {
            return parseFullDate(str);
        } else throw EXCEPTION;
    }

    private MyCell parseCellDate(String str) throws NumberFormatException, WrongFormulaFormatException {
        int curCol, curRow;
        curCol = str.charAt(0) - TableView.A + 1;
        curRow = Integer.parseInt(str.substring(1)) - 1;
        if (curRow >= table.getRowCount() || curCol >= table.getColumnCount())
            throw EXCEPTION;
        if (!notifier) {
            if (curRow == this.curRow && curCol == this.curCol)
                throw EXCEPTION;
        }
        if (table.getValueAt(curRow, curCol) == null) throw EXCEPTION;
        MyCell cellDate = (MyCell) table.getValueAt(curRow, curCol);
        if (cellDate == null)
            throw EXCEPTION;
        MyCell date = new MyCell(cellDate);
        if (!notifier) {
            cellDate.addUsingDependency(this.curRow, this.curCol);
        }
        return date;

    }

    private MyCell parseDate(String str) throws NumberFormatException, WrongFormulaFormatException {
        MyCell myCell = currentValue;
        if (str.length() >= 5) {
            switch (str.charAt(0)) {
                case SIGN_OF_EQUALITY: {
                    if(str.charAt(1)==M){
                        myCell = parseMaxOrMin(str);
                        myCell.setCurrentFormula(str);
                        break;
                    }
                    else {
                        myCell = parseEqualFormula(str);
                        myCell.setCurrentFormula(str);
                        break;
                    }
                }
                default: {
                    myCell = parseUsualDate(str);
                    myCell.setCurrentFormula(str);
                }
            }
        } else
            throw EXCEPTION;
        return myCell;
    }

    private void notifyCell(MyCell cell, int i, int j) throws WrongFormulaFormatException {
        HashSet<int[]> dependencies = cell.getUsingDependencies();
        MyCell tempCell = cell;
        cell = parseDate(cell.getCurrentFormula());
        cell.setUsingDependencies(tempCell.getUsingDependencies());
        table.setValueAt(cell, i, j);
        for (var elem : dependencies) {
            notifyCell((MyCell) table.getModel().getValueAt(elem[0], elem[1]), elem[0], elem[1]);
        }
    }

    public void notifyTable() throws WrongFormulaFormatException, NumberFormatException {
        notifier = true;
        MyCell cell;
        HashSet<int[]> dependencies = currentValue.getUsingDependencies();
        for (var elem : dependencies) {
            cell = (MyCell) table.getModel().getValueAt(elem[0], elem[1]);
            notifyCell(cell, elem[0], elem[1]);
        }
        notifier = false;
    }
}
