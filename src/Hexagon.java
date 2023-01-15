import java.awt.*;
/**
 * A public class that extends abstract Class shapes to render
 * a <strong>Hexagon</strong> on the canvas
 */
public class Hexagon extends Shapes{
    /**
     * Draws the hexagon using an abstract Graphics class
     * @param g Graphics g
     * @param x the x position of where Hexagon will be drawn
     * @param y the y position of where Hexagon will be drawn
     * @param size the value of Hexagon's side
     * @param fill the boolean value, for either filled or non-filled Triangle
     */
    public Hexagon(Graphics g, int x, int y, int size, boolean fill){drawHexagon(g,x,y,size,fill);}
}
