import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.Instant;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

/**
 * A class of the stopwatch
 * Computes the time difference and show in pictures
 */
public class Stopwatch implements MoveableShape
{
    private Dial outer;
    private Dial inner;
    private Instant upToNow;
    private boolean running;
    private boolean frozen;
    private double tick;


    /**
     * Construct a stopwatch
     * @param r the radius of the watch
     */
    public Stopwatch(int r)
    {
        outer = new Dial(r,true,Color.RED);
        inner = new Dial(r/3,false,new Color(255, 0, 255));
        upToNow = Instant.now();
        frozen = false;
        running = false;
        tick = 0;
    }

    /**
     * Draw the watch to the graphics
     * @param g2 the graphics2D to be drawn on
     */
    public void draw(Graphics2D g2)
    {
        JLabel label1 = new JLabel(outer);
        JLabel label2 = new JLabel(inner);
        label1.setBounds(0, 0, outer.getIconWidth(), outer.getIconHeight());
        label2.setBounds(outer.getIconWidth() / 2  - inner.getIconWidth() / 2,
                outer.getIconHeight() / 2 - inner.getIconHeight(),
                outer.getIconWidth(),
                outer.getIconHeight() / 2 + inner.getIconHeight() / 2);
        label1.paint(g2);
        label2.paint(g2);
    }

    /**
     * Move the hands of the watch
     */
    public void move()
    {
        if (running)
        {
            if (!frozen)
            {
                Instant n = Instant.now();
                Duration d = Duration.between(upToNow, n);
                int nano = d.getNano();
                long sec = d.getSeconds();
                double mili = nano / (Math.pow(10, 6));
                double second = mili / 1000;
                second = sec + second;
                tick = second / 60;
            }
        }
        double outerAng = 2 * Math.PI * (tick);
        double innerAng = 2 * Math.PI * (tick/60);
        outer.setP(outerAng);
        inner.setP(innerAng);
    }

    /**
     * An event of when the top button is pressed.
     */
    public void topButtonPressed()
    {
        if (running)
        {
            running = false;
            frozen = false;
        }
        else if (!running)
        {
            running = true;
            frozen = false;
            upToNow = Instant.now();
        }
    }

    /**
     * An event of when the second button is pressed.
     */
    public void secondButtonPressed()
    {
        if (!running)
        {
            outer.setP(0);
            inner.setP(0);
            tick = 0;
        }
        else
        {
            if (!frozen)
            {
                frozen = true;
            }
            else
            {
                frozen = false;
            }
        }
    }

}
