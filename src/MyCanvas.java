import javax.swing.*;
import java.awt.*;

public class MyCanvas extends Canvas {

    int xPos = 0; int yPos = 0;
    boolean fill;
    Color c;
    Graphics g;
    JLabel view;
    Cursor cursor;
    public MyCanvas(JLabel view, Graphics g){
        this.g = g;
        this.view = view;
        cursor = new Cursor(g, xPos-1, yPos-1);
    }
    public void drawRectangle(int width, int height){
        new Rectangle(g, xPos, yPos, width, height, fill);
        view.repaint();
    }

    public void drawTriangle(int size){
        new Triangle(g, xPos, yPos, size, fill);
        view.repaint();
    }

    public void drawOval(int size){
        new Oval(g, xPos, yPos, size, fill);
        view.repaint();
    }

    public void drawLine(int xTo, int yTo){
        new Line(g, xPos, yPos, xTo, yTo);
        cursor.moveCursor(g, xPos, yPos, xTo, yTo);
        xPos = xTo; yPos = yTo;
        view.repaint();
    }
}