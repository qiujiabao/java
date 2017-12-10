import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

/**
 * A composite shape that draws a car.
 */
public class HouseShapeMaker
{
   
   /**
    * make a house shape
    * @param x the start x-coord
    * @param y the start y-coord
    * @return the shape
    */
   public static Shape makeShape(int x, int y) 
   {
      final double WIDTH = 50;
      final double HEIGHT = 80;

      Rectangle2D.Double front = new Rectangle2D.Double(x, y + HEIGHT - WIDTH, WIDTH, WIDTH);
      Rectangle2D.Double door = new Rectangle2D.Double(x + WIDTH / 5, y + HEIGHT - WIDTH / 2, WIDTH / 5, WIDTH / 2);
      Rectangle2D.Double window = new Rectangle2D.Double(x + WIDTH * 3 / 5, y + HEIGHT - WIDTH / 2, WIDTH / 5, WIDTH / 5);
      Line2D.Double roofLeft = new Line2D.Double(x, y + HEIGHT - WIDTH, x + WIDTH / 2, y);
      Line2D.Double roofRight = new Line2D.Double(x + WIDTH, y + HEIGHT - WIDTH, x+ WIDTH / 2, y);

      CompositeShape houseShape = new CompositeShape();
      houseShape.add(front);
      houseShape.add(door);
      houseShape.add(window);
      houseShape.add(roofLeft);
      houseShape.add(roofRight);

      return houseShape;
   }
   
   /**
    * Main method
    * @param args array of arguments
    */
   public static void main(String[] args)
   {
      JOptionPane.showMessageDialog(null, // parent window
            "", // message
            "", // window title
            JOptionPane.INFORMATION_MESSAGE, // message type
            new ShapeIcon(makeShape(0, 0)));
   }
}
