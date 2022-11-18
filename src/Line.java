import java.awt.*;

/**
 *  A public class that extends abstract Class shapes
 *  in order to render a <strong>Line</strong> on the canvas
 */
public class Line extends Shapes{
    /**
     * Draws the line using an abstract Graphics class
     *
     * @param g Graphics g
     * @param xFrom the x position from which the line is drawing
     * @param yFrom the y position from which the line is drawing
     * @param xTo the x position to which the line is drawing
     * @param yTo the y position to which the line is drawing
     */
    public Line(Graphics g, int xFrom, int yFrom, int xTo, int yTo){
        drawLine(g, xFrom, yFrom, xTo, yTo);
    }
}
