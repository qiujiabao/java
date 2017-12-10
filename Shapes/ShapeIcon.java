import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.Icon;

/**
   An icon that is painted by drawing a Shape
*/
public class ShapeIcon implements Icon
{
   /**
      Construct a ShapeIcon.
      @param aShape the shape to use when painting
   */
   public ShapeIcon(Shape aShape)
   {
      shape = aShape;
   }

   /**
    * get width
    * @return width
    */
   public int getIconWidth()
   {
      return shape.getBounds().width + 1;
   }

   /**
    * get height
    * @return height
    */
   public int getIconHeight()
   {
       return shape.getBounds().height + 1;
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
      Rectangle bounds = shape.getBounds(); 
      Graphics2D g2 = (Graphics2D)g;
      g2.translate(x - bounds.getX(), y - bounds.getY());
      g2.setColor(Color.black);
      g2.draw(shape);
      g2.translate(-x + bounds.getX(), -y + bounds.getY());
   }

   private Shape shape;
}









