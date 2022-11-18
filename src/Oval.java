import java.awt.*;

/**
 * A public class that extends abstract Class shapes to render
 * an <strong>Oval</strong> on the canvas
 */
public class Oval extends Shapes{

    /**
     * Draws the oval using an abstract Graphics class
     * @param g Graphics g
     * @param x the x position of where Oval will be drawn
     * @param y the y position of where Oval will be drawn
     * @param size the value of Oval's radius
     * @param fill the boolean value, for either filled or non-filled oval
     */
    public Oval(Graphics g, int x, int y, int size, boolean fill){
        drawOval(g, x, y, size, fill);
    }
}
