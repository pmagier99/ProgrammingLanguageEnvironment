import javax.swing.*;
import java.awt.*;

/**
 * A public class that implements a <strong>Commands</strong> for
 * non drawing activities.
 * Also, it contains methods for drawing activities.
 */

public class MyCanvas implements Commands{

    int xPos; int yPos;
    boolean fill;
    Color c;
    Graphics g;
    JLabel view;
    Cursor cursor;

    /**
     * Creates a new Canvas on the provided <strong>JLabel</strong>
     * @param view - the JLabel that display graphics
     * @param g - Graphics g
     */
    public MyCanvas(JLabel view, Graphics g){
        this.g = g;
        this.view = view;
        xPos = 0; yPos = 0;
        cursor = new Cursor(g, 0, 0);
    }

    /**
     * Draws rectangle with the specified parameters
     * @param width value of the width
     * @param height value of the height
     */
    public void drawRectangle(int width, int height){
        g.setColor(c);
        new Rectangle(g, xPos, yPos, width, height, fill);
        view.repaint();
    }

    /**
     * Draws triangle with the specified parameter
     * @param size value of the size
     */
    public void drawTriangle(int size){
        g.setColor(c);
        new Triangle(g, xPos, yPos, size, fill);
        view.repaint();
    }

    /**
     * Draws oval with the specified parameter
     * @param size value of the size
     */
    public void drawOval(int size){
        g.setColor(c);
        new Oval(g, xPos, yPos, size, fill);
        view.repaint();
    }

    /**
     * Draws line with the specified parameters
     * @param xTo value of <strong>X</strong> where line should end
     * @param yTo value of <strong>Y</strong> where line should end
     */
    public void drawLine(int xTo, int yTo){
        g.setColor(c);
        new Line(g, xPos, yPos, xTo, yTo);
        cursor.moveCursor(g, xPos, yPos, xTo, yTo);
        xPos = xTo; yPos = yTo;
        view.repaint();
    }

    /**
     * Sets the value of <strong>fill</strong> to either
     * true or false.
     * @param fill <strong>on/off</strong>
     */
    @Override
    public void setFill(String fill) {
        g.setColor(c);
        if(fill.equals("on")){
            this.fill = true;
        }else if(fill.equals("off")){
            this.fill = false;
        }
    }

    /**
     * Sets the value of <strong>Color</strong>
     * @param colour the string name of Colour
     */
    @Override
    public void setColour(String colour) {
        switch (colour) {
            case "red":
                c = Color.red;
                break;
            case "yellow":
                c = Color.yellow;
                break;
            case "blue":
                c = Color.blue;
                break;
            case "green":
                c = Color.green;
                break;
        }
    }

    /**
     * Clears the entire canvas, but keeps the position
     * of cursor
     */
    @Override
    public void clear() {
        g.clearRect(0,0,550,550);
        cursor.moveCursor(g, xPos, yPos, xPos, yPos);
        view.repaint();
    }

    /**
     * Resets the position of cursor
     * to 0,0
     */
    @Override
    public void reset() {
        cursor.moveCursor(g, xPos, yPos, 0, 0);
        view.repaint();
        xPos = 0; yPos = 0;
    }

    @Override
    public void moveTo(int x, int y) {
        cursor.moveCursor(g, xPos, yPos, x, y);
        view.repaint();
        xPos = x; yPos = y;
    }
}