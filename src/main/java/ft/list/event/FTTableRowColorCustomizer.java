package ft.list.event;

import java.awt.Color;

/**
 * The RowColorCustomizer interface used to provide color customization for rows.
 * <p>The class that implements this interface can provide a specific <code>Color</code>
 * object for the background of the row based on the value of the row that is being
 * processed by the <code>FTTableList</code> component.<br />
 * The row object bean is passed to the <code>getRowColor</code> method.
 * 
 * @author Manuel.Agostinetto
 */
public interface FTTableRowColorCustomizer<E> {
    /**
     * Invoked when the <code>FTTableList</code> component is rendering a row
     * 
     * @param row the row to be rendered by the <code>FTTableList</code> component
     * @return the <code>Color</code> to use as background for the row
     */
    Color getRowColor(E row);
}
