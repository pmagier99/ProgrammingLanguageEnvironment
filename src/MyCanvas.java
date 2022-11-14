import javax.swing.*;
import java.awt.*;

public class MyCanvas extends Canvas {

    int xPos = 0; int yPos = 0;
    boolean fill;
    Color c;
    Graphics g;
    JLabel view;
    Dot d;
    public MyCanvas(JLabel view, Graphics g){
        this.g = g;
        this.view = view;
        d = new Dot(g, xPos, yPos);
    }
}