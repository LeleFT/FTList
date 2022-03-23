/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.list.models;

import java.util.ArrayList;

/**
 *
 * @author Manuel Agostinetto
 * 
 * @param <T> the type of elements managed by this model
 */
public class FTSimpleGenericModel<T> extends FTSimpleListModel<T> {
    
    private ArrayList<T> myData;
    
    /**
     * Creates a <code>FTSimpleGenericModel</code>.
     * Il <code>set</code> is <code>true</code> the model acts as a Set and
     * doesn't accept duplicate elements.
     * 
     * @param set if <code>true</code> it acts as a Set
     */
    public FTSimpleGenericModel(boolean set) {
        super( set );
        myData = new ArrayList<T>();
    }
    
    /**
     * Add an element to the model.
     * 
     * @param value the element to be added
     */
    @Override
    public void addElement(T value) {
        if (!set || !myData.contains(value)) {
            int first = myData.size();
            myData.add( value );
            fireIntervalAdded(this, first, myData.size()-1);
        }
    }
    
    /**
     * Removes all elements from the model.
     */
    @Override
    public void removeAllElements() {
        if ( !myData.isEmpty() ) {
            int last = myData.size() - 1;
            myData.clear();
            fireIntervalRemoved(this, 0, last);
        }
    }

    /**
     * Insert an element at the specified position into the model.
     * Every element from <code>index</code> will be shifted.
     * 
     * @param index the index where to insert the element
     * @param value the element to be inserted
     */
    @Override
    public void insertElementAt(int index, T value) {
        if (index >= 0) {
            if (myData.size() > index) {
                myData.add(index, value);
                fireIntervalAdded(this, index, index+1);
            } else {
                int first = myData.size();
                myData.add( value );
                fireIntervalAdded(this, first, myData.size()-1);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Index must be greater then 0. Passed " + index);
        }
    }
    
    /**
     * Remove the specified element, if present.
     * If the element is not contained in the model, it does nothing.
     * 
     * @param value the value to be removed from the model
     * @return <code>true</code> if the element was present in the model,
     * <code>false</code> otherwise.
     */
    @Override
    public boolean removeElement(T value) {
        boolean ret = false;
        if ( myData.contains(value) ) {
            int pos = myData.indexOf( value );
            ret = myData.remove(value);
            fireIntervalRemoved(this, pos, pos);
        }
        return ret;
    }
    
    /**
     * Removes the element at the specified position in the model.
     * If the index is outside of range, it does nothing.
     * 
     * @param index the index of the element to be removed from the model
     * @return the element that was removed or <code>null</code> if the index
     * is out of range
     */
    @Override
    public T removeElementAt(int index) {
        T ret = null;
        if ((index >= 0) && (index < myData.size())) {
            ret =  myData.remove( index );
            fireIntervalRemoved(this, index, index);
        }
        return ret;
    }
    
    @Override
    public T getElementAt(int index) { return myData.get( index ); }
    
    @Override
    public int getSize() { return myData.size(); }
}
