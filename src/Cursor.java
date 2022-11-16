import java.awt.*;

public class Cursor {

    Cursor(Graphics g, int x, int y){
        g.setColor(Color.RED);
        g.fillRect(x,y,3,3);
    }

    public void moveCursor(Graphics g, int x, int y, int newX, int newY){
        g.clearRect(x,y,3,3);
        new Cursor(g, newX, newY);
    }
}
