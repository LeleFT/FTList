package ft.list.models;

import ft.list.*;
import javax.swing.table.*;

/**
 * This abstract class provides default implementations for most of the methods
 * the <code>FTTableList</code> will use to interrogate a tabular data model.
 * <p><code>FTTableListModel</code> is natively designed for support set models
 * (that is, models that don't allow duplicate objects to be present). Subclasses
 * of <code>FTTableListModel</code> must take care about that property in order
 * to respect the contract of this semanthic.
 * <p>To get a concrete subclass of <code>FTTableListModel</code> for manipulating
 * simple data types (such as Strings and Numbers) you can take advantage of
 * the {@link FTModelFactory} factory class.
 * 
 * @author Manuel Agostinetto
 */
public abstract class FTTableListModel<E> extends AbstractTableModel {
    
    private String[] headers;
    
    /**
     * Specifies if this model is a Set. Default value is <code>false</code>.
     */
    protected boolean set;

    /**
     * Constructs a new <code>FTTableListModel</code> using the specified <code>headers</code> for the columns.
     * The model is not a Set.
     * 
     * @param headers an array of <code>String</code> used for the header of the table.
     */
    public FTTableListModel(String[] headers) {
        this(headers, false);
    }
    
    /**
     * Constructs a new <code>FTTableListModel</code> using the specified <code>headers</code>.
     * If <code>set</code> is <code>true</code>, the model is intended to be a Set.
     * 
     * @param headers an array of <code>String</code> used for the header of the table.
     * @param set if <code>true</code>, the model is a Set.
     */
    public FTTableListModel(String[] headers, boolean set) {
        this.headers = headers;
        this.set = set;
    }
    
    /**
     * Returns <code>true</code> if the model is a Set.
     * 
     * @return <code>true</code> if the model is a Set.
     */
    public boolean isSet() { return set; }
    
    /**
     * Returns the number of columns used in the model.
     * It reflects the number of columns displayed by the <code>FTTableList</code> object.
     * 
     * @return the number of columns used in the model.
     */
    @Override
    public int getColumnCount() {
        return headers.length;
    }

    /**
     * Returns the name of the column at <code>col</code> index.
     * It is the name of the column displayed in the table header.
     * 
     * @param col the index of the column
     * @return the name of the column at <code>col</code> index
     */
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
    
    /**
     * Returns the most specific superclass for all the cell values in the column.
     * This is used by the <code>FTTableList</code> to set up the default renderer
     * for the column.
     * 
     * @param col the index of the column
     * @return the common ancestor class of the object values in the model
     */
    @Override
    public Class<?> getColumnClass(int col) { return getValueAt(0, col).getClass(); }
    
    /**
     * This method returns always <code>false</code> in order to not allow cell editing.
     * 
     * @param row the index of the row in the table
     * @param col the index of the column in the table
     * @return always <code>false</code>
     */
    @Override
    public final boolean isCellEditable(int row, int col) { return false; }
    
    /**
     * Returns the object in the <code>row</code> index of the table.
     * 
     * @param row the index of the row
     * @return the object in the <code>row</code> index of the table.
     */
    public abstract E getRow(int row);
    
    /**
     * Adds a row to the table.
     * 
     * @param element the row to add to the table.
     */
    public abstract void addRow(E element);
    
    /**
     * Removes the row at the specified index. It returns the object corresponding
     * to the specified row that was removed.
     * 
     * @param row the index of the row to remove
     * @return the object corresponding to the row that was removed
     */
    public abstract E removeRow(int row);
    
    /**
     * Removes the specified object from the model. It returns <code>true</code> if
     * the specified object was present in the model.
     * 
     * @param element the object to remove from the model
     * @return <code>true</code> if the specified object was present in the model
     */
    public abstract boolean removeElement(E element);
    
    /**
     * Removes all the objects in the model, clearing the table.
     */
    public abstract void removeAllRows();
    
    /**
     * Add a row to the table at the specified index.
     * If index is greater than the size of the <code>FTTableList</code> component,
     * appends the row to the end of the table.<br />
     * If index is less than 0 an <code>ArrayIndexOutOfBoundsException</code> will be
     * thrown.
     * 
     * @param index the row index of the row to be inserted
     * @param element the row value to be inserted
     */
    public abstract void insertElementAt(int index, E element);
}
