package ft.list.models;

import javax.swing.AbstractListModel;

/**
 * Abstract class for FTSimpleListModel objects.
 * 
 * @author Manuel.Agostinetto
 * @param <E> the type of elements managed by this model
 */
public abstract class FTSimpleListModel<E> extends AbstractListModel {

    /**
     * Indicates whether this model acts as a Set or not.
     */
    protected boolean set;
    
    /**
     * Creates an instance of a <code>FTSimpleListModel</code> that doesn't
     * act as a Set.
     */
    public FTSimpleListModel() { this(false); }
    
    /**
     * Creates an instance of a <code>FTSimpleListModel</code>.
     * If <code>set</code> param is <code>true</code> it acts as a Set and
     * doesn't accept duplicate values.
     * 
     * @param set if <code>true</code> the model acts as a Set and doesn't
     * accept duplicate values
     */
    public FTSimpleListModel(boolean set) { this.set = set; }

    /**
     * Tells whether this model acts as a Set or not.
     * @return <code>true</code> if this model acts as a Set
     */
    public boolean isSet() { return set; }
    
    /**
     * Removes the element at the specified position from this model.
     * 
     * @param index the position of the element to remove
     * @return the element that was removed or <code>null</code> if index is
     * out of range
     */
    public abstract E removeElementAt(int index);

    /**
     * Removes the specified element from this model.
     * 
     * @param element the element to be removed
     * @return <code>true</code> if the element was in the model, <code>false</code>
     * otherwise
     */
    public abstract boolean removeElement(E element);

    /**
     * Removes all elements from this model.
     */
    public abstract void removeAllElements();

    /**
     * Add the specified element to the bottom of this model.
     * 
     * @param element the element to be added
     */
    public abstract void addElement(E element);

    /**
     * Insert an element at the specified position into the model.
     * Every element from <code>index</code> will be shifted.
     * 
     * @param index the index where to insert the element
     * @param element the element to be inserted
     */
    public abstract void insertElementAt(int index, E element);
}
