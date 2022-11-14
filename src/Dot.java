import java.awt.*;

public class Dot {

    Dot(Graphics g, int x, int y){
        g.setColor(Color.RED);
        g.fillRect(x,y,5,5);
    }

    public void moveDot(Graphics g, int x, int y, int newX, int newY){
        g.clearRect(x,y,5,5);
        g.fillRect(newX, newY, 5,5);
    }
}
