package ft.list;

import ft.list.models.FTSimpleListModel;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * An <code>FTList</code> implementation that uses a <code>JList</code> to represent
 * the objects contained in the list.To construct an <code>FTSimpleList</code> object
 it's necessary to pass it an <code>FTSimpleListModel</code> instance in the
 constructor.<br>
 To render the values in the list, it uses the default renderer provided by the
 <code>JList</code> class. It's also possible to set up a new Renderer for the
 list.
 * 
 * @author Manuel Agostinetto
 * @param <E> the type of elements managed by this list.
 */
public class FTSimpleList<E> extends FTList<E> {
    
    private class FTListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            notifyMyListListeners( lse );
        }
    }
    
    private FTSimpleListModel model;
    private JList list;
    private FTListListener myListListener;
    private ArrayList<ListSelectionListener> listeners;
    
    /**
     * Constructs a new <code>FTSimpleList</code> object using the specified <code>model</code>.
     * The new object uses the default button position for an <code>FTList</code> object.
     * 
     * @param model the model used to construct the <code>FTSimpleListModel</code>
     */
    public FTSimpleList(FTSimpleListModel<E> model) {
        this(model, SwingConstants.EAST);
    }
    
    /**
     * Constructs a new <code>FTSimpleList</code> object using the specified <code>model</code> and <code>buttonPosition</code>.
     * See {@link FTList#FTList(int) FTList(int)} for details about <code>buttonPosition</code> accepted values.
     * 
     * @param model the model used to construct the <code>FTSimpleListModel</code>
     * @param buttonPosition the position of the buttons in the component
     */
    public FTSimpleList(FTSimpleListModel<E> model, int buttonPosition) {
        super( buttonPosition );
        this.model = model;

        listeners = new ArrayList<ListSelectionListener>();
        
        initComponents();

        myListListener = new FTListListener();
        list.addListSelectionListener( myListListener );
    }
    
    @Override
    protected JComponent createListComponent() {
        list = new JList( model );
        return list;
    }
    
    @Override
    public boolean isEmpty() { return (model.getSize() == 0); }
    
    @Override
    public boolean removeElement(E element) { return model.removeElement( element ); }

    @Override
    public int getNumElements() { return model.getSize(); }

    @Override
    public int[] getSelectedIndexes() { return list.getSelectedIndices(); }

    @Override
    public java.util.List<E> getAllElements() {
        ArrayList<E> ret = new ArrayList<E>();
        if (model.getSize() > 0) {
            int rowCount = model.getSize();
            for(int i=0; i<rowCount; i++) {
                ret.add( (E) model.getElementAt(i) );
            }
        }
        return ret;
    }

    @Override
    public E getElementAt(int index) { return (E) model.getElementAt(index); }

    @Override
    public E removeElementAt(int index) { return (E) model.removeElementAt(index); }

    @Override
    public void addElements(Collection<? extends E> c) {
        for(E element : c) {
            model.addElement( element );
        }
    }

    @Override
    public void add(E element) { model.addElement( element ); }

    @Override
    public void removeAllElements() { model.removeAllElements(); }
    
    @Override
    public void clearSelection() { list.clearSelection(); }
    
    @Override
    public void refreshList() {
        list.repaint();
    }
    
    @Override
    public void setSelectionInterval(int firstIndex, int secondIndex) {
        list.getSelectionModel().setSelectionInterval(firstIndex, firstIndex);
    }
    
    /**
     * Adds a listener to the list, to be notified each time a change to the selection occurs;
     * the preferred way of listening for selection state changes.
     * <code>FTSimpleList</code> takes care of listening for selection state changes
     * in the selection model, and notifies the given listener of each change.
     * @param listener the <code>ListSelectionListener</code> to add.
     */
    @Override
    public void addListSelectionListener(ListSelectionListener listener) { listeners.add( listener ); }
    
    @Override
    public void setSelectionMode(int selectionMode) { list.setSelectionMode( selectionMode ); }
    
    /**
     * Sets a renderer for the list.
     * A renderer is a delegate that is used to paint each row in the list.
     * 
     * @param renderer the renderer instance to set up for the list
     */
    public void setRenderer(ListCellRenderer renderer) {
        list.setCellRenderer( renderer );
    }
    
    private void notifyMyListListeners(ListSelectionEvent evt) {
        ListSelectionEvent lse = new ListSelectionEvent(this, evt.getFirstIndex(), evt.getLastIndex(), evt.getValueIsAdjusting());
        for(ListSelectionListener lsl : listeners) lsl.valueChanged( lse );
    }
}
