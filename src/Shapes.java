import java.awt.*;

/**
 * An abstract class that contains all methods for drawing shapes
 */
abstract class Shapes {

    /**
     * It draws the rectangle with the specified arguments.
     * @param g Graphics g
     * @param x the x position of where Rectangle will be drawn
     * @param y the y position of where Rectangle will be drawn
     * @param width the value of Rectangle's width
     * @param height the value of Rectangle's height
     * @param fill the boolean value, for either filled or non-filled oval
     */
    public void drawRectangle(Graphics g, int x, int y, int width, int height, boolean fill){

        if (fill) g.fillRect(x, y, width, height); else g.drawRect(x, y, width, height);
    }

    /**
     * It draws the oval with the specified arguments.
     * @param g Graphics g
     * @param x the x position of where Oval will be drawn
     * @param y the y position of where Oval will be drawn
     * @param size the value of Oval's radius
     * @param fill the boolean value, for either filled or non-filled oval
     */
    public void drawOval(Graphics g, int x, int y, int size, boolean fill){
        if(fill) g.fillOval(x, y, size, size); else g.drawOval(x, y, size, size);
    }

    /**
     * It draws the line with the specified arguments.
     *
     * @param g Graphics g
     * @param xFrom the x position from which the line is drawing
     * @param yFrom the y position from which the line is drawing
     * @param xTo the x position to which the line is drawing
     * @param yTo the y position to which the line is drawing
     */
    public void drawLine(Graphics g, int xFrom, int yFrom, int xTo, int yTo){
        g.drawLine(xFrom, yFrom, xTo, yTo);
    }


    /**
     * Draws the equilateral triangle with the specified arguemnts.
     * It uses the following formula to calcualte the height of triangle:
     * height=(side*Math.sqrt(3))/2
     *
     * @param g Graphics g
     * @param x the x position of where Triangle will be drawn
     * @param y the y position of where Triangle will be drawn
     * @param size the value of Triangle's side
     * @param fill the boolean value, for either filled or non-filled Triangle
     */

    //x1 y1 - start point
    //x2 y2 - x+size y1
    //x3 y3 - x+size/2 y=(size*Math.sqrt(3))/2
    public void drawTriangle(Graphics g, int x, int y, int size, boolean fill){
        int[] xArray = {x, x+size, x+(size/2)};
        int[] yArray = {y, y, y - (int) Math.floor((size*Math.sqrt(3))/2)};

        if(fill) g.fillPolygon(xArray, yArray, 3); else g.drawPolygon(xArray, yArray, 3);
    }
}
