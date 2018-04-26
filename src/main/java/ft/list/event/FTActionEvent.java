package ft.list.event;

/**
 * An event which indicates that a button of a {@link ft.list.FTList} object is clicked
 * The event is passed to every {@link FTActionListener} object that registered
 * to receive such events using the <code>addFTActionListener</code> method.
 * 
 * @author Manuel Agostinetto
 */
public class FTActionEvent extends java.util.EventObject {
    
    /**
     * The <code>ADD_ACTION</code> Event. This event is generated when the Add button is clicked.
     */
    public static final int ADD_ACTION = 0;
    
    /**
     * The <code>REMOVE_ACTION</code> Event. This event is generated when the Remove button is clicked.
     */
    public static final int REMOVE_ACTION = 1;
    
    /**
     * The <code>UP_ACTION</code> Event. This event is generated when the Up button is clicked.
     */
    public static final int UP_ACTION = 2;
    
    /**
     * The <code>DOWN_ACTION</code> Event. This event is generated when the Down button is clicked.
     */
    public static final int DOWN_ACTION = 3;
    
    private int action;
    
    /**
     * Construct an <code>FTActionEvent</code> object.
     * <p>Note that passing in an invalid <code>action</code> results in unspecified
     * behavior. This method throws an <code>IllegalArgumentException</code> if
     * <code>source</code> is <code>null</code>.
     * 
     * @param source  The <code>FTList</code> that originated the event
     * @param action  An integer identifying the button that was clicked
     */
    public FTActionEvent(Object source, int action) {
        super( source );
        this.action = action;
    }
    
    /**
     * Returns the button that originated this event. The button is identifyed by
     * one of these values:
     * <ul>
     *    <li><code>ADD_ACTION</code></li> 
     *    <li><code>REMOVE_ACTION</code></li> 
     *    <li><code>UP_ACTION</code></li> 
     *    <li><code>DOWN_ACTION</code></li> 
     * </ul>
     * 
     * @return the value of the button that was clicked.
     */
    public int getAction() { return action; }
    
    /**
     * Returns a <code>String</code> representation of this event.
     * @return a <code>String</code> represtntation of this event.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FTActionEvent: fired ");
        switch( action ) {
            case ADD_ACTION   : sb.append("ADD_ACTION")   ; break;
            case REMOVE_ACTION: sb.append("REMOVE_ACTION"); break;
            case UP_ACTION    : sb.append("UP_ACTION")    ; break;
            case DOWN_ACTION  : sb.append("DOWN_ACTION")  ; break;
            default: sb.append("UNKNOWN_ACTION")          ; break;
        }
        sb.append(" Event from ").append( (source != null) ? source.toString() : "Unknown source" );
        return sb.toString();
    }
}
