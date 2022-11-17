import javax.swing.*;
import java.awt.*;

public class MyCanvas extends Canvas implements Commands{

    int xPos = 0; int yPos = 0;
    boolean fill;
    Color c;
    Graphics g;
    JLabel view;
    Cursor cursor;
    public MyCanvas(JLabel view, Graphics g){
        this.g = g;
        this.view = view;
        cursor = new Cursor(g, 0, 0);
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

    @Override
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void setColour(Color c) {
        this.c = c;
    }

    @Override
    public void clear(JLabel view, Graphics g) {
        new MyCanvas(view, g);
    }

    @Override
    public void reset() {
        cursor.moveCursor(g, xPos, yPos, 0, 0);
        xPos = 0; yPos = 0;
    }

    @Override
    public void moveTo(int x, int y) {
        cursor.moveCursor(g, xPos, yPos, x, y);
        xPos = x; yPos = y;
    }
}