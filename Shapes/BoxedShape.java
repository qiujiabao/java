import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A class to make a boxed shape
 * with the given shape
 */
public class BoxedShape implements Shape
{

    /**
     * The constructor of the class
     * @param shape the given shape
     * @param padding the padding (in pixel)
     */
    public BoxedShape(Shape shape, int padding)
    {
        this.shape = shape;
        this.padding = padding;
    }

    /**
     * Get the bounds
     * @return the bounds as a Rectangle
     */
    public Rectangle getBounds()
    {
        Rectangle r = (Rectangle) shape.getBounds().clone();
        int x = (int) (r.getX() - padding - 1);
        int y = (int) (r.getY() - padding - 1);
        int w = (int) (r.getWidth() + padding * 2 + 2);
        int h = (int) (r.getHeight() + padding * 2 + 2);
        r.setBounds(x, y, w, h);
        return r;
    }

    /**
     * Get the bounds in 2D
     * @return the bounds as Rectangle2D
     */
    public Rectangle2D getBounds2D()
    {
        Rectangle2D r = (Rectangle2D) shape.getBounds2D().clone();
        double x = r.getX() - padding - 1;
        double y = r.getY() - padding - 1;
        double w = r.getWidth() + padding * 2 + 2;
        double h = r.getHeight() + padding * 2 + 2;
        Rectangle2D r2 = new Rectangle2D.Double(x, y, w, h);
        return r2;
    }

    /**
     * Check if the shape contains the point
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @return true if it contains
     */
    public boolean contains(double x, double y)
    {
        return getBounds().contains(x, y);
    }

    /**
     * Check if the shape contains the point
     * @param p the point
     * @return true if it contains
     */
    public boolean contains(Point2D p)
    {
        return getBounds2D().contains(p);
    }

    /**
     * Check if the shape intersects the rectangle
     * @param x the start x-coordinate of rectangle
     * @param y the start y-coordinate of rectangle
     * @param w the width of rectangle
     * @param h the height of rectangle
     * @return true if it intersects
     */
    public boolean intersects(double x, double y, double w, double h)
    {
        Rectangle2D r = new Rectangle2D.Double(x, y, w, h);
        return r.intersects(getBounds2D());
    }

    /**
     * Check if the shape intersects the rectangle
     * @param r the rectangle
     * @return true if it intersects
     */
    public boolean intersects(Rectangle2D r)
    {
        return r.intersects(getBounds2D());
    }

    /**
     * Check if the shape contains the rectangle
     * @param x the start x-coordinate of rectangle
     * @param y the start y-coordinate of rectangle
     * @param w the width of rectangle
     * @param h the height of rectangle
     * @return true if it contains
     */
    public boolean contains(double x, double y, double w, double h)
    {
        Rectangle2D r = new Rectangle2D.Double(x, y, w, h);
        return getBounds2D().contains(r);
    }

    /**
     * Check if the shape contains the rectangle
     * @param r the rectangle
     * @return true if it contains
     */
    public boolean contains(Rectangle2D r)
    {
        return getBounds2D().contains(r);
    }

    /**
     * Get the path iterator of the shape
     * @param at the AffineTransform of the iterator
     * @return the path iterator
     */
    public PathIterator getPathIterator(AffineTransform at)
    {
        return new BoxedShapePathIterator(shape, padding, at);
    }

    /**
     * Get the path iterator of the shape
     * @param at the AffineTransform of the iterator
     * @param flatness the flatness of the iterator
     * @return the path iterator
     */
    public PathIterator getPathIterator(AffineTransform at, double flatness)
    {
        return new BoxedShapePathIterator(shape, padding, at, flatness);
    }
    
    private Shape shape;
    private int padding;

}
