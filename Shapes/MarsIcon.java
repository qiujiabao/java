import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
   An icon that has the shape of the planet Mars.
*/
public class MarsIcon implements Icon
{
   /**
      Constructs a Mars icon of a given size.
      @param aSize the size of the icon
   */
   public MarsIcon(int aSize)
   {
      size = aSize;
   }

   /**
    * get width
    * @return the width
    */
   public int getIconWidth()
   {
      return size;
   }

   /**
    * get height
    * @return height
    */
   public int getIconHeight()
   {
      return size;
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
      Graphics2D g2 = (Graphics2D) g;
      Ellipse2D.Double planet = new Ellipse2D.Double(x, y,
            size, size);
      g2.setColor(Color.RED);
      g2.fill(planet);
      g2.setColor(Color.BLACK);
   }

   private int size;
}
