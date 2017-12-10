import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
   An icon that contains a moveable shape.
*/
public class ShapeIcon implements Icon
{
   /**
    * Creates a shape icon.
    * @param shape the movable shape to be used
    * @param width the width of current icon
    * @param height the height of current icon
    */
   public ShapeIcon(MoveableShape shape,
      int width, int height)
   {
      this.shape = shape;
      this.width = width;
      this.height = height;
   }
   
   /**
    * Get the width of current icon.
    * @return the width
    */
   public int getIconWidth()
   {
      return width;
   }

   /**
    * Get the height of current icon.
    * @return the height
    */
   public int getIconHeight()
   {
      return height;
   }

   /**
    * Paint current icon.
    * @param c the component of swing
    * @param g the graphics of swing
    * @param x the start x-coordinate
    * @param y the start y-coordinate
    */
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      shape.draw(g2);
   }

   private int width;
   private int height;
   private MoveableShape shape;
}


