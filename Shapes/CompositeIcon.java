import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * A class compositeS multiple icons
 */
public class CompositeIcon implements Icon
{
    /**
     * The constructor of the class
     */
    public CompositeIcon()
    {
         icons = new ArrayList<>();
    }

    /**
     * Paint the icons
     * @param c the Component
     * @param g the Graphics
     * @param x the start x-coordinate
     * @param y the start y-coordinate
     */
    public void paintIcon(Component c, Graphics g, int x, int y) 
    {
        for (Icon i : icons)
        {
            i.paintIcon(c, g, x, y);
        }
    }

    /**
     * Add an icon to this composite
     * @param i the icon to be added
     */
    public void add(Icon i)
    {
        icons.add(i);
    }

    /**
     * Get the icon width after composition
     * @return the width
     */
    public int getIconWidth() 
    {
        if (icons.isEmpty()) return 0;
        else
        {
            int width = icons.get(0).getIconWidth();
            for (Icon i : icons)
            {
                if (width < i.getIconWidth()) width = i.getIconWidth();
            }
            return width;
        }
    }

    /**
     * Get the icon height after composition
     * @return the height
     */
    public int getIconHeight() 
    {
        if (icons.isEmpty()) return 0;
        else
        {
            int height = icons.get(0).getIconHeight();
            for (Icon i : icons)
            {
                if (height < i.getIconHeight()) height = i.getIconHeight();
            }
            return height;
        }
    }

    private ArrayList<Icon> icons;
}
