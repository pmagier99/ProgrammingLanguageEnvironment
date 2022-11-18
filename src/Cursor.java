import java.awt.*;

/**
 * A public class Cursor that render a small rectangle on the canvas
 * that then moves whenever the user calls command <strong>moveto</strong>
 *
 */
public class Cursor {
    /**
     *
     * @param g Graphics g
     * @param x x Position of where Cursor needs to be placed
     * @param y y Position of where Cursor needs to be placed
     */
    Cursor(Graphics g, int x, int y){
        g.setColor(Color.RED);
        g.fillRect(x,y,3,3);
    }

    /**
     * Clears the previous location of cursor and
     * generates a new one.
     *
     * @param g Graphics g
     * @param x current x Position
     * @param y current y Position
     * @param newX new x Position
     * @param newY new Y Position
     */
    public void moveCursor(Graphics g, int x, int y, int newX, int newY){
        g.clearRect(x,y,3,3);
        new Cursor(g, newX, newY);
    }
}
