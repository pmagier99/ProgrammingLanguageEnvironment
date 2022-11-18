import java.awt.*;

/**
 * A public class that extends abstract Class shapes to render
 * an <strong>Rectangle</strong> on the canvas
 */
public class Rectangle extends Shapes{
    /**
     * Draws the rectangle using an abstract Graphics class
     * @param g Graphics g
     * @param x the x position of where Rectangle will be drawn
     * @param y the y position of where Rectangle will be drawn
     * @param width the value of Rectangle's width
     * @param height the value of Rectangle's height
     * @param fill the boolean value, for either filled or non-filled oval
     */
    public Rectangle(Graphics g, int x, int y, int width, int height, boolean fill){
        drawRectangle(g, x, y, width, height, fill);
    }
}
