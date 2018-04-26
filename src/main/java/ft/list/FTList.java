package ft.list;

import ft.list.event.FTActionEvent;
import ft.list.event.FTSelectionObservable;
import ft.list.event.FTActionListener;
import ft.list.models.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ListSelectionListener;
/**
 * A component that displays a list of objects and four buttons with the semantic
 * of allowing the user to add other objects, remove objects from the list, select
 * one or more items and moving them up and down in the list.<br />
 * By default, the buttons are placed to the EAST side of the component.
 * <p>The library provides two concrete implementation of this abstract class,
 * {@link FTSimpleList} and {@link FTTableList}, that differs each other from the
 * main component that displays objects.
 * <p><b>FTSimpleList</b><br />
 * Displays objects using a JList component. Objects are mantained in a
 * <code>FTSimpleListModel</code> instance passed to its constructor.
 * <p><b>FTTableList</b><br />
 * Displays objects using a JTable component. Objects are mantained in a
 * <code>FTTableListModel</code> instance passed to its constructor.
 * <p><strong>Warning:</strong> subclasses must call <code>initComponents</code>
 * method after construction in order to perform graphical initialization and layout.
 * 
 * <p>Listeners can be registered to the list in order to receive events from the
 * buttons clicked. A class that is interested in receive such events must implements
 * the <code>FTActionListener</code> interface and register using the
 * <code>addFTActionListener</code> method.
 * 
 * @author Manuel Agostinetto
 */
public abstract class FTList<E> extends JPanel implements FTSelectionObservable {
    
    private class FTInternalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            FTButton btn = (FTButton) ae.getSource();
            notifyMyListeners( btn.getIndex() );
        }
    }
    
    private class FTButton extends JButton {

        private int index;

        public FTButton(String icon, int index) {
            super( loadIcon(icon + ".png") );
            this.index = index;
            setPreferredSize( new Dimension(18, 18) );
            setRolloverIcon( loadIcon(icon + "_r.png") );
            setRolloverSelectedIcon( loadIcon(icon + "_r.png") );
            setPressedIcon( loadIcon(icon + "_p.png") );
            setBorder( BorderFactory.createEmptyBorder() );
            setContentAreaFilled( false );
            setFocusPainted( false );
        }

        public int getIndex() { return index; }
    }

    private FTInternalListener myInternalListener;
    
    private ArrayList<FTActionListener> listeners;
    private ArrayList<ListSelectionListener> listListeners;
    
    private JComponent myList;
    private JPanel legendPanel;
    private FTButton cmdAdd;
    private FTButton cmdRemove;
    private FTButton cmdUp;
    private FTButton cmdDown;
    private java.util.List<FTLegendItem> legend;
    
    private boolean[] enabledButtons;
    private int buttonPosition;
    private int legendPosition;
    private static final Map<Integer,String> mapPosition;
    
    /**
     * Creates a new <code>FTList</code>. The default button position is used.
     * 
     * @see FTList#FTList(int) 
     */
    public FTList() {
        this(SwingConstants.EAST);
    }
    
    /**
     * Creates a new <code>FTList</code> placing the buttons in the <code>buttonPosition</code>
     * side of the component.<br />
     * The value of <code>buttonPosition</code> must be one of
     * <ul>
     *    <li><code>SwingConstants.EAST:</code> (default) The buttons are placed
     *        in the EAST (right) side of the component</li>
     *    <li><code>SwingConstants.WEST:</code> The buttons are placed in the WEST
     *        (left) side of the component</li>
     *    <li><code>SwingConstants.NORTH:</code> The buttons are placed in the
     *        NORTH (top) side of the component.</li>
     *    <li><code>SwingConstants.SOUTH:</code> The buttons are placed in the
     *        SOUTH (bottom) side of the component.</li>
     * </ul>
     * @param buttonPosition One of the following constants defined in <code>SwingConstants:
     *        EAST, WEST, NORTH</code> or <code>SOUTH</code>
     */
    public FTList(int buttonPosition) {
        this.buttonPosition = buttonPosition;
        setPreferredSize( new Dimension(100, 100) );
        enabledButtons = new boolean[4];
        legendPosition = (buttonPosition == SwingConstants.SOUTH) ? SwingConstants.NORTH : SwingConstants.SOUTH;
    }
    
    /**
     * Sets the visibility of the <code>Add</code> button. The default value is
     * <code>true</code>
     * 
     * @param visible if <code>true</code>, the <code>Add</code> button is visible
     */
    public void setAddButtonVisible(boolean visible) { cmdAdd.setVisible( visible ); }
    
    /**
     * Sets the visibility of the <code>Remove</code> button. The default value is
     * <code>true</code>
     * 
     * @param visible if <code>true</code>, the <code>Remove</code> button is visible
     */
    public void setRemoveButtonVisible(boolean visible) { cmdRemove.setVisible( visible ); }

    /**
     * Sets the visibility of the <code>Up</code> button. The default value is
     * <code>true</code>
     * 
     * @param visible if <code>true</code>, the <code>Up</code> button is visible
     */
    public void setUpButtonVisible(boolean visible) { cmdUp.setVisible( visible ); }

    /**
     * Sets the visibility of the <code>Down</code> button. The default value is
     * <code>true</code>
     * 
     * @param visible if <code>true</code>, the <code>Down</code> button is visible
     */
    public void setDownButtonVisible(boolean visible) { cmdDown.setVisible( visible ); }
    
    /**
     * Enabled (or disables) the <code>Add</code> button. The default value is
     * <code>true</code>
     * 
     * @param enabled if <code>true</code>, the <code>Add</code> button is enabled
     */
    public void setAddButtonEnabled(boolean enabled) {
        cmdAdd.setEnabled( enabled );
        enabledButtons[0] = enabled;
    }

    /**
     * Enabled (or disables) the <code>Remove</code> button. The default value is
     * <code>false</code>
     * 
     * @param enabled if <code>true</code>, the <code>Remove</code> button is enabled
     */
    public void setRemoveButtonEnabled(boolean enabled) {
        cmdRemove.setEnabled( enabled );
        enabledButtons[1] = enabled;
    }

    /**
     * Enabled (or disables) the <code>Up</code> button. The default value is
     * <code>false</code>
     * 
     * @param enabled if <code>true</code>, the <code>Up</code> button is enabled
     */
    public void setUpButtonEnabled(boolean enabled) {
        cmdUp.setEnabled( enabled );
        enabledButtons[2] = enabled;
    }
    
    /**
     * Enabled (or disables) the <code>Down</code> button. The default value is
     * <code>false</code>
     * 
     * @param enabled if <code>true</code>, the <code>Down</code> button is enabled
     */
    public void setDownButtonEnabled(boolean enabled) {
        cmdDown.setEnabled( enabled );
        enabledButtons[3] = enabled;
    }

    /**
     * Sets a legend for this list component.
     * A legend is a list of <code>FTLegendItem</code> that can be rendered at the bottom
     * of the table to help users understand the meaning of the colors of rows.
     * <p>Legend items are shown in the order they are provided by the list.
     * 
     * @param legend a list of <code>FTLegendItem</code> that represents the legend.
     */
    public void setLegend(java.util.List<FTLegendItem> legend) {
        this.legend = legend;
        remakeLegend();
    }
    
    /**
     * Sets the visibility property of the legend.
     * Legend is not visible by default.
     * 
     * @param legendVisible <code>true</code> if the legend should be visible
     */
    public void setLegendVisible(boolean legendVisible) {
        legendPanel.setVisible( legendVisible );
    }
    
    /**
     * Adds an <code>FTActionListener</code> to the list
     * 
     * @param listener the <code>FTActionListener</code> to be added
     */
    public void addFTActionListener(FTActionListener listener) { listeners.add( listener ); }
    
    /**
     * Removes an <code>FTActionListener</code> from the list
     * 
     * @param listener the <code>FTActionListener</code> to be removed
     */
    public void removeFTActionListener(FTActionListener listener) { listeners.remove( listener ); }
    
    /**
     * Sets whether or not this component is enabled.
     * Overrides the implementation in <code>JComponent</code> in order to reflect
     * the state for all its children according to the actual state of the buttons.
     * <p>For example, let's have an FTList object called <code>ftList</code>,
     * with the Remove button disabled. Execute this snipped, doesn't change the
     * state of the Remove button:
     * <p>&nbsp;</p>
     * <pre>
     * ftList.setEnabled( false );   // Disable the whole FTList
     * ftList.setEnabled( true );    // Re-enable the whole FTList
     * </pre>
     * 
     * @param enabled true if this component should be enabled, false otherwise
     * @see javax.swing.JComponent#setEnabled(boolean) 
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled( enabled );
        myList.setEnabled( enabled );
        cmdAdd.setEnabled( enabled && enabledButtons[0] );
        cmdRemove.setEnabled( enabled && enabledButtons[1] );
        cmdUp.setEnabled( enabled && enabledButtons[2] );
        cmdDown.setEnabled( enabled && enabledButtons[3] );
    }
    
    /**
     * Initialize the internal graphic components and layout of the list.
     * Subclasses must call this method after construction.
     */
    protected final void initComponents() {
        setLayout( new BorderLayout() );
        
        myInternalListener = new FTInternalListener();
        listeners = new ArrayList<FTActionListener>();
        listListeners = new ArrayList<ListSelectionListener>();
        
        JPanel jpButtons = constructButtonPanel();
        createButtons( jpButtons );
        
        legendPanel = new JPanel( new FlowLayout(FlowLayout.LEFT, 0, 10) );
        legendPanel.setVisible( false );
        
        myList = createListComponent();
        JScrollPane jsp = new JScrollPane( myList );
        
        add(jpButtons, mapPosition.get(buttonPosition));
        add(jsp, BorderLayout.CENTER);
        add(legendPanel, mapPosition.get(legendPosition));
    }
    
    private void remakeLegend() {
        legendPanel.removeAll();
        if ((legend != null) && !legend.isEmpty()) {
            for(FTLegendItem item : legend) {
                legendPanel.add( createLegendItem(item.getColor(), item.getDescription()) );
            }
        }
        legendPanel.revalidate();
    }
    
    private void createButtons(JPanel jp) {
        cmdAdd = new FTButton("resources/add", FTActionEvent.ADD_ACTION);
        cmdAdd.addActionListener( myInternalListener );
        cmdAdd.setToolTipText("Add");
        enabledButtons[0] = true;
        
        cmdRemove = new FTButton("resources/remove", FTActionEvent.REMOVE_ACTION);
        cmdRemove.addActionListener( myInternalListener );
        cmdRemove.setToolTipText("Remove");
        cmdRemove.setEnabled( false );
        enabledButtons[1] = false;
        
        cmdUp = new FTButton("resources/up", FTActionEvent.UP_ACTION);
        cmdUp.addActionListener( myInternalListener );
        cmdUp.setToolTipText("Move Up");
        cmdUp.setEnabled( false );
        enabledButtons[2] = false;

        cmdDown = new FTButton("resources/down", FTActionEvent.DOWN_ACTION);
        cmdDown.addActionListener( myInternalListener );
        cmdDown.setToolTipText("Move Down");
        cmdDown.setEnabled( false );
        enabledButtons[3] = false;

        jp.add( cmdAdd );
        jp.add( cmdRemove );
        jp.add( cmdUp );
        jp.add( cmdDown );
    }
    
    private Icon loadIcon(String resource) {
        return new ImageIcon(FTList.class.getResource(resource));
    }
    
    private JPanel constructButtonPanel() {
        JPanel jp = null;
        
        Dimension dimPanel = null;
        switch( buttonPosition ) {
            case SwingConstants.WEST:
            case SwingConstants.EAST:
            case SwingConstants.LEFT:
            case SwingConstants.RIGHT:
                jp = new JPanel( new FlowLayout(FlowLayout.CENTER, 5, 5) );
                dimPanel = new Dimension(30, 100);
                break;
                
            default:
                jp = new JPanel( new FlowLayout(FlowLayout.LEFT, 5, 5) );
                dimPanel = new Dimension(100, 30);
        }
        jp.setPreferredSize( dimPanel );
        jp.setMaximumSize( dimPanel );
        return jp;
    }
    
    private JPanel createLegendItem(Color color, String label) {
        JPanel jp = new JPanel( new BorderLayout() );
        jp.setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 10) );

        JLabel lblColore = new JLabel();
        lblColore.setOpaque(true);
        lblColore.setBackground(color);
        lblColore.setBorder( BorderFactory.createLineBorder(Color.BLACK, 1));
        lblColore.setPreferredSize( new Dimension(15, 15) );

        JLabel lblEtichetta = new JLabel( label );
        lblEtichetta.setHorizontalAlignment( JLabel.LEFT );
        lblEtichetta.setBorder( BorderFactory.createEmptyBorder(0, 5, 0, 0) );

        jp.add(lblColore, BorderLayout.WEST);
        jp.add(lblEtichetta, BorderLayout.CENTER);

        return jp;
    }
    
    private void notifyMyListeners(int btn) {
        FTActionEvent evt = new FTActionEvent(this, btn);
        for(FTActionListener ftal : listeners) {
            ftal.buttonClicked( evt );
        }
    }
    
    /**
     * Removes all elements from the list. Subclasses must implements this method
     * removing all elements from the model and notifying the list.
     */
    public abstract void removeAllElements();
    
    /**
     * Adds an element to the list. Subclasses must implements this method adding
     * the element to the model and notifying the list.
     * 
     * @param element the element to be added to the list
     */
    public abstract void add(E element);
    
    /**
     * Adds all the elements in the <code>Collection</code> to the list. Subclasses
     * must implements this metohd adding all the elements in the specified
     * <code>Collection</code> to the model and notifying the list.
     * @param c the <code>Collection</code> of elements to be added to the list
     */
    public abstract void addElements(Collection<? extends E> c);
    
    /**
     * Sets the selections mode to be used by the list.
     * <p>The following list describes the accepted selection modes:
     * <ul>
     *    <li><code>ListSelectionModel.SINGLE_SELECTION</code> - Only one element
     *        in the list can be selected at a time</li>
     *    <li><code>ListSelectionModel.SINGLE_INTERVAL_SELECTION</code> - Only one
     *        contiguous interval of elements in the list can be selected at a time</li>
     *    <li><code>ListSelectionModel.MULTIPLE_INTERVAL_SELECTION</code> - In this
     *        mode, there's no restriction on what elements can be selected in the
     *        list. This mode is the default</li>
     * </ul>
     * @param selectionMode the selection mode
     */
    public abstract void setSelectionMode(int selectionMode);
    
    /**
     * Deselects all items in the list.
     */
    public abstract void clearSelection();
    
    /**
     * Refreshes the list.
     * This component will redraw itself. Every change made to data content or renderers
     * will be applied to the UI.
     */
    public abstract void refreshList();
    
    /**
     * Changes the selection to be between index0 and index1 inclusive.
     * firstIndex doesn't have to be less than or equal to secondIndex.
     * <p>In <code>SINGLE_SELECTION</code> selection mode, only the second index is used.
     * <p>If this represents a change to the current selection, then each
     * <code>ListSelectionListener</code> is notified of the change.
     * 
     * @param firstIndex first index of the selection interval
     * @param secondIndex second index of the selection interval
     */
    public abstract void setSelectionInterval(int firstIndex, int secondIndex);
    
    /**
     * Removes the element at the specified position in the list. Shifts any
     * subsequent elements to the top.
     * 
     * @param index the position of the element to be removed
     * @return the element that was removed from the list or <code>null</code> if
     *         the index is out of range (<code>index < 0 || index >= getNumElements()</code>)
     */
    public abstract E removeElementAt(int index);

    /**
     * Returns the element at the specified position in the list.
     * 
     * @param index the index of the element in the list to return
     * @return the element at the specified position in the list
     * @throws <code>ArrayIndexOutOfBoundsException</code> if the index is out of
     *         range (<code>index < 0 || index >= getNumElements()</code>)
     */
    public abstract E getElementAt(int index);
    
    /**
     * Returns all the elements in the list as a {@link java.util.List List} object.
     * @return a <code>List</code> of all the objects
     */
    public abstract java.util.List<E> getAllElements();
    
    /**
     * Returns an array af all the selected indexes in the list.
     * @return all of the selected indices or an empty array if nothing is selected
     */
    public abstract int[] getSelectedIndexes();
    
    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    public abstract int getNumElements();
    
    /**
     * Removes the specified element from the list, if it is present.
     * If the list does not contain the element, it is unchanged. More formally,
     * removes the element with the lowest index <code>i</code> such that<br />
     * <code>(o==null ? getElementAt(i)==null : o.equals(getElementAt(i)))</code>
     * (if such an element exists).<br />
     * Returns true if this list contained the specified
     * element (or equivalently, if this list changed as a result of the call).
     * 
     * @param element element to be removed from this list, if it is present
     * @return <chde>true</code> if this list contained the specified element
     */
    public abstract boolean removeElement(E element);
    
    /**
     * Returns <code>true</code> if this list contains no elements.
     * @return <code>true</code> if this list contains no elements
     */
    public abstract boolean isEmpty();
    
    /**
     * Creates the main component of the <code>FTList</code> object.
     * Subclasses must implement this method in order to provide a <code>JComponent</code>
     * object that is Scrollable and capable to handle the objects in the model.
     * Such object will be placed in a <code>JScrollPane</code> and than added to
     * the main place of this component.
     * 
     * @return an instance of <code>JComponent</code> for the main part of this list
     */
    protected abstract JComponent createListComponent();
    
    static {
        mapPosition = new HashMap<Integer,String>();
        mapPosition.put(SwingConstants.EAST, BorderLayout.EAST);
        mapPosition.put(SwingConstants.WEST, BorderLayout.WEST);
        mapPosition.put(SwingConstants.SOUTH, BorderLayout.SOUTH);
        mapPosition.put(SwingConstants.NORTH, BorderLayout.NORTH);
    }
}
