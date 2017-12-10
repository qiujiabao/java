import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.JOptionPane;

/**
 * the text shape maker
 */
public class TextShapeMaker
{
   /**
    * make the shape
    * @param message the message it uses
    * @param f the font is uses
    * @return the text shape
    */
   public static Shape makeShape(String message, Font f)
   {
      FontRenderContext context = new FontRenderContext()
      {
      };
      TextLayout layout = new TextLayout(message, f, context);
      Shape outline = layout.getOutline(context.getTransform());
      return outline;
   }

   /**
    * main method
    * @param args the array of arguments
    */
   public static void main(String[] args)
   {
      JOptionPane.showMessageDialog(null, // parent window
            "", // message
            "", // window title
            JOptionPane.INFORMATION_MESSAGE, // message type
            new ShapeIcon(makeShape("Hello", new Font("Serif", Font.PLAIN, 100))));
   }
}
