import java.awt.*;
import java.awt.geom.*;
import java.util.StringTokenizer;

import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A class that draw a dial of a watch
 */
public class Dial implements Icon
{
    private int r;
    private Color c;
    private boolean threeLevel;
    private double pX;
    private double pY;

    /**
     * Construct a dial.
     * @param r the radius of the dial
     * @param b true if the dial should have 3 levels of ticks
     * @param c the color of the hand
     */
    public Dial(int r, boolean b, Color c)
    {
        this.r = r;
        this.c = c;
        threeLevel = b;
        pX = r;
        pY = 0;
    }

    /**
     * Paint this dial.
     * @param c the component of the swing
     * @param g the graphics of the swing
     * @param x the start x-coordinate
     * @param y the start y-coordinate
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        pX = pX + x;
        pY = pY + y;
        Graphics2D g2 = (Graphics2D)g;
        double startX = r + x;
        double startY = y;
        double cX = r + x;
        double cY = r + y;
        if (threeLevel)
        {
            for (int i = 0; i < 300; i++)
            {
                Line2D.Double tmp = new Line2D.Double(startX,startY,startX,startY+r/32);
                g2.rotate(Math.PI / 150,cX,cY);
                g2.draw(tmp);
            }
            for (int i = 0; i < 60; i++)
            {
                Line2D.Double tmp = new Line2D.Double(startX,startY,startX,startY+r/16);
                g2.rotate(Math.toRadians(360/60),cX,cY);
                g2.draw(tmp);
            }
            for (int i = 0; i < 12; i++)
            {
                Line2D.Double tmp = new Line2D.Double(startX,startY,startX,startY+r/8);
                g2.rotate(Math.toRadians(360/12),cX,cY);
                g2.draw(tmp);
            }
        }
        else
        {
            for (int i = 0; i < 60; i++)
            {
                Line2D.Double tmp = new Line2D.Double(startX,startY,startX,startY+r/16);
                g2.rotate(Math.toRadians(360/60),cX,cY);
                g2.draw(tmp);
            }
            for (int i = 0; i < 12; i++)
            {
                Line2D.Double tmp = new Line2D.Double(startX,startY,startX,startY+r/8);
                g2.rotate(Math.toRadians(360/12),cX,cY);
                g2.draw(tmp);
            }
        }
        for (int i = 0; i < 12; i++)
        {
            String toDraw = Integer.toString(i*5);

            JLabel label = new JLabel();
            label.setText(toDraw);
            Font f = new Font(g2.getFont().getName(), Font.BOLD, r/6);
            label.setFont(f);
            label.setBounds(0, 0, r, r);
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setHorizontalAlignment(SwingConstants.LEADING);

            double smallR = r - r / 4;
            double xCor = cX + smallR * Math.cos(Math.PI/2 - i * Math.PI/6);
            double yCor = cY - smallR * Math.sin(Math.PI/2 - i * Math.PI/6);
            if (i == 0)
            {
                xCor = xCor - r/18;
                yCor = yCor - r/12;
            }
            else
            {
                xCor = xCor - r/10;
                yCor = yCor - r/12;
            }

            g2.translate(xCor, yCor);
            label.paint(g2);
            g2.translate(-xCor, -yCor);

            ArrowHead a = new ArrowHead();
            Point2D.Double p = new Point2D.Double(cX, cY);
            Point2D.Double q = new Point2D.Double(pX, pY);
            a.draw(g2, p, q, this.c, r);
            g2.draw(new Line2D.Double(p,q));
        }
    }

    /**
     * Set the point of the top of the hand,
     * using the angle (in rads) between
     * the 0 tick and the current position.
     * @param a the angle in rads
     */
    public void setP(double a)
    {
        pX = r + r * Math.cos(Math.PI/2 - a);
        pY = r - r * Math.sin(Math.PI/2 - a);
    }

    /**
     * Get the width of current icon.
     * @return the width
     */
    public int getIconWidth()
    {
        return 2*r;
    }

    /**
     * Get the height of current icon.
     * @return the height
     */
    public int getIconHeight()
    {
        return 2*r;
    }

    /**
     * This class is adapted from Violet-0.16b source code
     * deleted unused methods & fields
     * original copyright by Cay S. Horstmann
     */
    private class ArrowHead
    {
       /**
        * The constructor.
        */
       private ArrowHead() {}
       
       /**
          Draws the arrowhead.
          @param g2 the graphics context
          @param p a point on the axis of the arrow head
          @param q the end point of the arrow head
       */
       public void draw(Graphics2D g2, Point2D p, Point2D q, Color c, int size)
       {
          GeneralPath path = getPath(p, q, size);
          g2.setColor(c);
          g2.fill(path);
          g2.draw(path);
       }

       /**
          Gets the path of the arrowhead
          @param p a point on the axis of the arrow head
          @param q the end point of the arrow head
          @return the path
       */
       public GeneralPath getPath(Point2D p, Point2D q, int size)
       {
          GeneralPath path = new GeneralPath();
          if (this == new ArrowHead()) return path;
          final double ARROW_ANGLE = Math.PI / 6;
          final double ARROW_LENGTH = size / 20;

          double dx = q.getX() - p.getX();
          double dy = q.getY() - p.getY();
          double angle = Math.atan2(dy, dx);
          double x1 = q.getX() 
             - ARROW_LENGTH * Math.cos(angle + ARROW_ANGLE);
          double y1 = q.getY() 
             - ARROW_LENGTH * Math.sin(angle + ARROW_ANGLE);
          double x2 = q.getX() 
             - ARROW_LENGTH * Math.cos(angle - ARROW_ANGLE);
          double y2 = q.getY() 
             - ARROW_LENGTH * Math.sin(angle - ARROW_ANGLE);

          path.moveTo((float)q.getX(), (float)q.getY());
          path.lineTo((float)x1, (float)y1);
          path.lineTo((float)x2, (float)y2);
          path.closePath();    
          return path;
       }
    }
}
