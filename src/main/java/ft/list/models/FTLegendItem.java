package ft.list.models;

import java.awt.Color;

/**
 * An item of the legend for a <code>FTList</code> instance.
 * This class represents an item of the legend: an item is an object defined as a color
 * and a description.
 * 
 * @author Manuel.Agostinetto
 */
public class FTLegendItem {
    private Color color;
    private String description;
    
    /**
     * Creates a new <code>FTLegendItem</code>.
     * It represent a color and a description.
     * 
     * @param color the color for which this item is in the legend
     * @param description the textual description of that's color meaning
     */
    public FTLegendItem(Color color, String description) {
        this.color = color;
        this.description = description;
    }

    /**
     * Gets the <code>Color</code> object for which to provide a legend.
     * @return the <code>Color</code> object for the legend item
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the description of the color for the legend.
     * @return the description for the legend
     */
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "FTLegendItem[color=" + color.toString() + "; description=" + description + ']';
    }
}
