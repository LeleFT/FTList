package ft.list.event;

/**
 * The listener interface for receiving action events from an <code>FTList</code> object.
 * The class that is interested in processing an action event implements this interface,
 * and the object created with that class is registered with a <code>FTList</code> component,
 * using the component's <code>addFTActionListener</code> method.
 * When the action event occurs, that object's <code>buttonClicked</code> method is invoked. 
 * 
 * @author Manuel Agostinetto
 */
public interface FTActionListener {
    /**
     * Invoked when a button of an {@link ft.list.FTList} object is clicked.
     * See the class description for {@link FTActionEvent} for a definition of a FTAction event.
     * 
     * @param evt The FTAction event fired by the button
     */
    void buttonClicked(FTActionEvent evt);
}
