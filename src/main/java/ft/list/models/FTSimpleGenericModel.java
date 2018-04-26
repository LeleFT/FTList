/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.list.models;

import java.util.ArrayList;

/**
 *
 * @author Manuel Agostinetto
 */
public class FTSimpleGenericModel<T> extends FTSimpleListModel<T> {
    
    private ArrayList<T> myData;
    
    public FTSimpleGenericModel(boolean set) {
        super( set );
        myData = new ArrayList<T>();
    }
    
    @Override
    public void addElement(T value) {
        if (!set || !myData.contains(value)) {
            int first = myData.size();
            myData.add( value );
            fireIntervalAdded(this, first, myData.size()-1);
        }
    }
    
    @Override
    public void removeAllElements() {
        if ( !myData.isEmpty() ) {
            int last = myData.size() - 1;
            myData.clear();
            fireIntervalRemoved(this, 0, last);
        }
    }

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
