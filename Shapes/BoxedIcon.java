import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/**
 * A class to make a boxed icon
 * with the given icon
 */
public class BoxedIcon implements Icon
{
    /**
     * Constructor for the class
     * @param icon the given icon
     * @param padding the padding (in pixel)
     */
    public BoxedIcon(Icon icon, int padding)
    {
        this.icon = icon;
        this.padding = padding;
    }

    /**
     * Paint the icon
     * @param c the Component
     * @param g the Graphics
     * @param x the start x-coordinate
     * @param y the start y-coordinate
     */
    public void paintIcon(Component c, Graphics g, int x, int y) 
    {
        Graphics2D g2 = (Graphics2D)g;
        icon.paintIcon(c, g2, x + padding + 1, y + padding + 1);
        int w = icon.getIconWidth() + padding * 2;
        int h = icon.getIconHeight() + padding * 2;
        Rectangle2D.Double r = new Rectangle2D.Double(x, y, w, h);
        g2.draw(r);
    }

    /**
     * Get the boxed icon width
     * @return the width
     */
    public int getIconWidth() 
    {
        return icon.getIconWidth() + padding * 2 + 2;
    }

    /**
     * Get the boxed icon height
     * @return the height
     */
    public int getIconHeight() 
    {
        return icon.getIconHeight() + padding * 2 + 2;
    }

    private Icon icon;
    private int padding;
}
