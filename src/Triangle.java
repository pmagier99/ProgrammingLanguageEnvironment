import java.awt.*;

/**
 * A public class that extends abstract Class shapes to render
 * an <strong>Triangle</strong> on the canvas
 */
public class Triangle extends Shapes{
    /**
     * Draws the triangle using an abstract Graphics class
     * @param g Graphics g
     * @param x the x position of where Triangle will be drawn
     * @param y the y position of where Triangle will be drawn
     * @param size the value of Triangle's side
     * @param fill the boolean value, for either filled or non-filled Triangle
     */
    public Triangle(Graphics g, int x, int y, int size, boolean fill){
        drawTriangle(g, x, y,size, fill);
    }
}
