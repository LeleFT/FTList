package ft.list.event;

import javax.swing.event.ListSelectionListener;

/**
 * The observable interface that allows an <code>FTList</code> object to register
 * a <code>ListSelectionListener</code>.<br />
 * The <code>ListSelectionListener</code> object will be notified for changes made
 * to the selection model of the list.
 * 
 * @author Manuel.Agostinetto
 */
public interface FTSelectionObservable {
    /**
     * Adds the specified <code>listener</code> to receive notifications from this <code>FTList</code>.
     * 
     * @param listener the listener to be added to the <code>FTList</code>
     */
    void addListSelectionListener(ListSelectionListener listener);
}
