package ft.list;

import ft.list.event.FTTableRowColorCustomizer;
import ft.list.models.FTLegendItem;
import ft.list.models.FTTableListModel;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * An <code>FTList</code> implementation that uses a <code>JTable</code> to represent
 * the objects contained in the list. To construct an <code>FTTableList</code> object
 * it's necessary to pass it an <code>FTTableListModel</code> instance in the
 * constructor.<br />
 * To render the values in the table, it uses the default renderer provided by the
 * <code>JTable</code> class. It's also possible to set up a new Renderer for each
 * column of the table.
 * 
 * @author Manuel Agostinetto
 */
public class FTTableList<E> extends FTList<E> {
    
    private class FTTableListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            notifyMyListListeners( lse );
        }
    }
    
    private class FTCustomTable extends JTable {

        private FTTableListModel<E> model;

        public FTCustomTable(FTTableListModel<E> tableModel) {
            super( tableModel );
            model = tableModel;
        }

        @Override
        public void setModel(TableModel model) {
            if (model instanceof FTTableListModel) {
                super.setModel( model );
                this.model = (FTTableListModel<E>) model;
            }
        }
        
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component rendererComponent = super.prepareRenderer(renderer, row, column);

            if (rowColorCustomizer != null) {
                if ( !isCellSelected(row, column) ) {
                    E rowObj = model.getRow( convertRowIndexToModel(row) );
                    Color color = rowColorCustomizer.getRowColor( rowObj );
                    if (color != null) {
                        rendererComponent.setBackground( color );
                    }
                }
            }

            return rendererComponent;
        }
    }
    
    private FTTableListModel<E> model;
    private FTCustomTable table;
    private FTTableListListener myListListener;
    private ArrayList<ListSelectionListener> listeners;
    private FTTableRowColorCustomizer<E> rowColorCustomizer;
    
    /**
     * Constructs a new <code>FTTableList</code> object using the specified <code>model</code>.
     * The new object uses the default button position for an <code>FTList</code> object.
     * 
     * @param model the model used to construct the <code>FTTableList</code>
     */
    public FTTableList(FTTableListModel<E> model) {
        this(model, SwingConstants.EAST);
    }
    
    /**
     * Constructs a new <code>FTTableList</code> object using the specifiled <code>model</code> and <code>buttonPosition</code>.
     * See {@link FTList#FTList(int) FTList(int)} for details about <code>buttonPosition</code> accepted values.
     * 
     * @param model the model used to construct the <code>FTTableList</code>
     * @param buttonPosition the position of the buttons in the component
     */
    public FTTableList(FTTableListModel<E> model, int buttonPosition) {
        super( buttonPosition );
        this.model = model;
        
        listeners = new ArrayList<ListSelectionListener>();
        
        initComponents();

        myListListener = new FTTableListListener();
        table.getSelectionModel().addListSelectionListener( myListListener );
    }
    
    @Override
    protected JComponent createListComponent() {
        table = new FTCustomTable( model );
        return table;
    }
    
    @Override
    public boolean isEmpty() { return (model.getRowCount() == 0); }
    
    @Override
    public boolean removeElement(E element) { return model.removeElement( element ); }
    
    @Override
    public int getNumElements() { return model.getRowCount(); }
    
    @Override
    public int[] getSelectedIndexes() { return table.getSelectedRows(); }
    
    @Override
    public java.util.List<E> getAllElements() {
        ArrayList<E> ret = new ArrayList<E>();
        if (model.getRowCount() > 0) {
            int rowCount = model.getRowCount();
            for(int i=0; i<rowCount; i++) {
                ret.add( model.getRow(i) );
            }
        }
        return ret;
    }
    
    @Override
    public E getElementAt(int index) { return model.getRow(index); }
    
    @Override
    public E removeElementAt(int index) { return model.removeRow(index); }
    
    @Override
    public void addElements(Collection<? extends E> c) {
        for(E element : c) {
            model.addRow( element );
        }
    }
    
    @Override
    public void add(E element) { model.addRow( element ); }
    
    @Override
    public void removeAllElements() { model.removeAllRows(); }
    
    @Override
    public void clearSelection() { table.clearSelection(); }
    
    @Override
    public void refreshList() {
        table.repaint();
    }
    
    /**
     * Adds a listener to the list, to be notified each time a change to the selection occurs; the preferred way of listening for selection state changes.
     * <code>FTTableList</code> takes care of listening for selection state changes
     * in the selection model, and notifies the given listener of each change.
     * @param listener the <code>ListSelectionListener</code> to add.
     */
    @Override
    public void addListSelectionListener(ListSelectionListener listener) { listeners.add( listener ); }
    
    @Override
    public void setSelectionMode(int selectionMode) { table.setSelectionMode( selectionMode ); }
    
    @Override
    public void setSelectionInterval(int firstIndex, int secondIndex) {
        table.getSelectionModel().setSelectionInterval(firstIndex, firstIndex);
    }
    
    /**
     * Sets a renderer for the specified column.
     * A renderer is a delegate that is used to paint each cell in the column.
     * 
     * @param columnIndex the index of the column to which the renderer will be set
     * @param renderer the renderer instance to set up for the column
     * @throws <code>ArrayIndexOutOfBoundsException</code> if <code>columnIndex</code>
     *         is out of range.
     */
    public void setRenderer(int columnIndex, TableCellRenderer renderer) {
        if (columnIndex < model.getColumnCount()) {
            table.getColumn( model.getColumnName(columnIndex) ).setCellRenderer( renderer );
            table.repaint();
        } else {
            throw new ArrayIndexOutOfBoundsException( columnIndex );
        }
    }
    
    /**
     * Sets a RowColorCustomizer for this table component.<br />
     * A RowColorCustomizer is a delegate that is used to determine the color to use for
     * highlighting the entire row of a table according to the value of that row bean.
     * 
     * @param rowColorCustomizer the customizer instanze to set up
     */
    public void setRowColorCustomizer(FTTableRowColorCustomizer<E> rowColorCustomizer) {
        this.rowColorCustomizer = rowColorCustomizer;
    }
    
    /**
     * Specify if the user is allowed to reorder columns in the table.
     * 
     * @param allow <code>true</code> if the user is allowed to reorder columns
     */
    public void setColumnReorderingAllowed(boolean allow) {
        table.getTableHeader().setReorderingAllowed( allow );
    }
    
    /**
     * Sets the height of the rows of the table.
     * 
     * @param height the row height in pixels
     */
    public void setRowHeight(int height) {
        table.setRowHeight( height );
    }
    
    private void notifyMyListListeners(ListSelectionEvent evt) {
        ListSelectionEvent lse = new ListSelectionEvent(this, evt.getFirstIndex(), evt.getLastIndex(), evt.getValueIsAdjusting());
        for(ListSelectionListener lsl : listeners) lsl.valueChanged( lse );
    }
}
