import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 * A class to composite the shape iterators
 */
public class CompositeShapePathIterator implements PathIterator
{
    /**
     * The constructor of the class
     */
    public CompositeShapePathIterator()
    {
        iters = new ArrayList<>();
    }

    /**
     * To add a path iterator
     * @param pathIterator the iterator to be added
     */
    public void add(PathIterator pathIterator)
    {
        iters.add(pathIterator);
        if (iter == null) iter = iters.get(0);
    }

    /**
     * Get the winding rule of composite path iterator
     * @return the winding rule
     */
    public int getWindingRule()
    {
        return iter.getWindingRule();
    }

    /**
     * Check if the whole iterator is done
     * @return true if is done
     */
    public boolean isDone()
    {
        return isDone;
    }

    /**
     * Move to the next of the iterator
     */
    public void next()
    {
        iter.next();
        if (iter.isDone()) 
        {
            int i = iters.indexOf(iter) + 1;
            if (i < iters.size()) iter = iters.get(i);
            else isDone = true;
        }
    }

    /**
     * Get current coordinates and segment type of the composite iterator
     * @param coords the array holds the data returned from this method
     * @return the path segment type
     */
    public int currentSegment(float[] coords)
    {
        return iter.currentSegment(coords);
    }

    /**
     * Get current coordinates and segment type of the composite iterator
     * @param coords the array holds the data returned from this method
     * @return the path segment type
     */
    public int currentSegment(double[] coords)
    {
        return iter.currentSegment(coords);
    }

    private ArrayList<PathIterator> iters;
    private PathIterator iter;
    private boolean isDone;

}
