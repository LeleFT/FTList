/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.list.models;

import java.util.ArrayList;

/**
 * This class represents a generic model for a <code>FTTableList</code>.
 * 
 * @author Manuel.Agostinetto
 * @param <T> the type of elements managed by this model.
 */
public class FTTableGenericModel<T> extends FTTableListModel<T[]> {
    private ArrayList<T[]> myData;
    
    /**
     * Creates an instance of <code>FTTableGenericModel</code> with a header.
     * If the <code>set</code> param is set to <code>true</code> the model acts
     * as a Set and doesn't accept duplicate elements.
     * 
     * @param headers the array of Strings used for the table header
     * @param set if <code>true</code> the model acts as a Set and doesn't
     * accept duplicate values
     */
    public FTTableGenericModel(String[] headers, boolean set) {
        super(headers, set);
        myData = new ArrayList<T[]>();
    }
    
    @Override
    public void removeAllRows() {
        int size = myData.size();
        myData.clear();
        fireTableRowsDeleted(0, size);
    }
    
    @Override
    public void insertElementAt(int index, T[] row) {
        if (index >= 0) {
            if (myData.size() > index) {
                myData.add(index, row);
                fireTableRowsInserted(index, index);
            } else {
                myData.add( row );
                fireTableRowsInserted(myData.size()-1, myData.size()-1);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Index must be greater then 0. Passed " + index);
        }
    }
    
    @Override
    public boolean removeElement(T[] row) {
        boolean ret = false;
        int index = myData.indexOf( row );
        if (index >= 0) {
            ret = myData.remove( row );
            fireTableRowsDeleted(index, index);
        }
        return ret;
    }
    
    @Override
    public T[] removeRow(int index) {
        T[] ret = null;
        if (index >= 0) {
            ret = myData.remove( index );
            fireTableRowsDeleted(index, index);
        }
        return ret;
    }
    
    @Override
    public void addRow(T[] row) {
        if (!set || !myData.contains(row)) {
            int size = myData.size();
            myData.add( row );
            fireTableRowsInserted(size, myData.size()-1);
        }
    }
    
    @Override
    public T[] getRow(int index) {
        return myData.get( index );
    }
    
    @Override
    public T getValueAt(int row, int col) {
        T ret = null;
        if (row < myData.size()) {
            if (col < getColumnCount()) {
                ret = myData.get( row )[ col ];
            }
        }
        return ret;
    }
    
    @Override
    public int getRowCount() {
        return myData.size();
    }
}
