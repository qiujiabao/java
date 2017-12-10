import java.awt.*;
import javax.swing.*;

/**
 * Make a shifted icon
 */
public class ShiftedIcon implements Icon
{
   /**
    * constructor of the class
    * @param icon the icon to be shifted
    * @param x the shift amount on x-axis
    * @param y the shift amount on y-axis
    */
   public ShiftedIcon(Icon icon, int x, int y)
   {
      this.icon = icon;
      this.shiftx = x;
      this.shifty = y;
   }

   /**
    * get width
    * @return width
    */
   public int getIconWidth()
   {
      return icon.getIconWidth() + shiftx;
   }

   /**
    * git height
    * @return height
    */
   public int getIconHeight()
   {
      return icon.getIconHeight() + shifty;
   }

   /**
    * Paint the icon
    * @param c component used
    * @param g graphics used
    * @param x start x-coords
    * @param y start y-coords
    */
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      icon.paintIcon(c, g, x + shiftx, y + shifty);
   }

   private Icon icon;
   private int shiftx;
   private int shifty;
}
