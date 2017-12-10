import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
   A shape that is composed of several individual shapes.
*/
public class CompositeShape implements Shape
{
   /**
      Constructs an empty composite shape.
   */
   public CompositeShape()
   {
      shapes = new ArrayList<>();
   }

   /**
      Add a shape to this composite shape.
      @param aShape the shape to add
   */
   public void add(Shape aShape)
   {
      shapes.add(aShape);
   }

   /**
    * Check if the shape contains the point
    * @param x the x-coord of the point
    * @param y the y-coord of the point
    * @return true if contains
    */
   public boolean contains(double x, double y)
   {
      for (Shape s : shapes)
      {
         if (s.contains(x, y)) return true;
      }
      return false;
   }
      
   /**
    * Check if the shape contains the rectangle
    * @param x the x-coord of the rect
    * @param y the y-coord of the rect
    * @param w the width of the rect
    * @param h the height of the rect
    * @return true if contains
    */
   public boolean contains(double x, double y, double w, double h)
   {
      for (Shape s : shapes)
      {
         if (s.contains(x, y, w, h)) return true;
      }
      return false;
   }
      
   /**
    * Check if the shape contains the point
    * @param p the point
    * @return true if contains
    */
   public boolean contains(Point2D p)
   {
      for (Shape s : shapes)
      {
         if (s.contains(p)) return true;
      }
      return false;
   }

   /**
    * Check if the shape contains the rectangle
    * @param r the rectangle
    * @return true if contains
    */
   public boolean contains(Rectangle2D r)
   {
      for (Shape s : shapes)
      {
         if (s.contains(r)) return true;
      }
      return false;
   }
      
   /**
    * Get the bounds
    * @return the bounds as rectangle
    */
   public Rectangle getBounds()
   {
      if (shapes.size() == 0) return new Rectangle();
      Shape s = shapes.get(0);
      Rectangle r = s.getBounds();
      for (int i = 1; i < shapes.size(); i++)
      {
         s = shapes.get(i);
         r = r.union(s.getBounds());
      }
      return r;
   }

   /**
    * Get the bounds
    * @return the bounds as rectangle2D
    */
   public Rectangle2D getBounds2D()
   {
      if (shapes.size() == 0) return new Rectangle2D.Double();
      Shape s = shapes.get(0);
      Rectangle2D r = s.getBounds2D();
      for (int i = 1; i < shapes.size(); i++)
      {
         s = shapes.get(i);
         Rectangle2D.union(r, s.getBounds(), r);
      }
      return r;
   }

   /**
    * Get path iterator
    * @param at the at to draw it
    * @return the path iterator
    */
   public PathIterator getPathIterator(AffineTransform at)
   {
      CompositeShapePathIterator iterator 
         = new CompositeShapePathIterator();
      for (Shape s : shapes)
      {
         iterator.add(s.getPathIterator(at));
      }
      return iterator;
   }

   /**
    * Get path iterator
    * @param at the at to draw it
    * @param flatness the flatness to draw it
    * @return the path iterator
    */
   public PathIterator getPathIterator(AffineTransform at,double flatness)
   {
      CompositeShapePathIterator iterator 
         = new CompositeShapePathIterator();
      for (Shape s : shapes)
      {
         iterator.add(s.getPathIterator(at, flatness));
      }
      return iterator;
   }

   /**
    * Check if it intersects the rectangle
    * @param x the x-coord of the rect
    * @param y the y-coord of the rect
    * @param w the width of the rect
    * @param h the height of the rect
    * @return true if intersects
    */
   public boolean intersects(double x, double y, double w, double h)
   {
      for (Shape s : shapes)
      {
         if (s.intersects(x, y, w, h)) return true;
      }
      return false;
   }

   /**
    * Check if it intersects the rectangle
    * @param r the rect
    * @return true if intersects
    */
   public boolean intersects(Rectangle2D r)
   {
      for (Shape s : shapes)
      {
         if (s.intersects(r)) return true;
      }
      return false;
   }

   private ArrayList<Shape> shapes;
}
