import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;

/**
 * A class provided
 */
public class BoxedShapePathIterator implements PathIterator
{
   /**
    * The constructor
    * @param shape the shape to be boxed
    * @param padding the padding in pixel
    * @param at the AT to draw it
    */
   public BoxedShapePathIterator(Shape shape, int padding, AffineTransform at)
   {
      this.padding = padding;
      this.shape = shape;
      this.at = at;
      iter = shape.getPathIterator(at);
      steps = 0;
   }

   /**
    * The constructor
    * @param shape the shape to be boxed
    * @param padding the padding in pixel
    * @param at the AT to draw it
    * @param flatness the flatness to draw it
    */
   public BoxedShapePathIterator(Shape shape, int padding, AffineTransform at, double flatness)
   {
      this.padding = padding;
      this.shape = shape;
      this.at = at;
      iter = shape.getPathIterator(at, flatness);
      steps = 0;
   }

   /**
    * Get current segment
    * @param coords the coordinate to return
    * @return the segment type
    */
   public int currentSegment(double[] coords)
   {
      Rectangle2D bounds = shape.getBounds2D();
      System.out.println(bounds);
      double[] corners = new double[] { bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY() };
      double[] transformedCorners;
      if (at == null)
         transformedCorners = corners;
      else
      {
         transformedCorners = new double[4];
         at.transform(corners, 0, transformedCorners, 0, 2);
      }

      double xmin = transformedCorners[0];
      double ymin = transformedCorners[1];
      double xmax = transformedCorners[2];
      double ymax = transformedCorners[3];
      if (steps == 0)
      {
         coords[0] = xmin - padding;
         coords[1] = ymin - padding;
         return PathIterator.SEG_MOVETO;
      }
      else if (steps == 1)
      {
         coords[0] = xmax + padding;
         coords[1] = ymin - padding;
         return PathIterator.SEG_LINETO;
      }
      else if (steps == 2)
      {
         coords[0] = xmax + padding;
         coords[1] = ymax + padding;
         return PathIterator.SEG_LINETO;
      }
      else if (steps == 3)
      {
         coords[0] = xmin - padding;
         coords[1] = ymax + padding;
         return PathIterator.SEG_LINETO;
      }
      else if (steps == 4)
      {
         coords[0] = xmin - padding;
         coords[1] = ymin - padding;
         return PathIterator.SEG_LINETO;
      }
      else if (steps == 5)
      {
         return PathIterator.SEG_CLOSE;
      }
      else
         return iter.currentSegment(coords);
   }

   /**
    * Get current segment
    * @param coords the coordinate to return
    * @return the segment type
    */
   public int currentSegment(float[] coords)
   {
      double[] dcoords = new double[coords.length];
      int result = currentSegment(dcoords);
      for (int i = 0; i < coords.length; i++)
         coords[i] = (float) dcoords[i];
      return result;
   }

   /**
    * Get winding rule
    * @return winding rule
    */
   public int getWindingRule()
   {
      return iter.getWindingRule();
   }

   /**
    * Check if done
    * @return true if done
    */
   public boolean isDone()
   {
      return steps > 5 && iter.isDone();
   }

   /**
    * To next segment
    */
   public void next()
   {
      if (steps <= 5)
         steps++;
      else
         iter.next();
   }

   private Shape shape;
   private PathIterator iter;
   private int padding;
   private int steps;
   private AffineTransform at;
}