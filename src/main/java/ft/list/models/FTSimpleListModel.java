package ft.list.models;

import javax.swing.AbstractListModel;

/**
 *
 * @author Manuel.Agostinetto
 */
public abstract class FTSimpleListModel<E> extends AbstractListModel {
    protected boolean set;
    
    public FTSimpleListModel() { this(false); }
    
    public FTSimpleListModel(boolean set) { this.set = set; }

    public boolean isSet() { return set; }
    
    public abstract E removeElementAt(int index);
    public abstract boolean removeElement(E element);
    public abstract void removeAllElements();
    public abstract void addElement(E element);
    public abstract void insertElementAt(int index, E element);
}
