import javax.swing.*;
import java.awt.*;

public class MyCanvas implements Commands{

    int xPos; int yPos;
    boolean fill;
    Color c;
    Graphics g;
    JLabel view;
    Cursor cursor;
    public MyCanvas(JLabel view, Graphics g){
        this.g = g;
        this.view = view;
        xPos = 0; yPos = 0;
        cursor = new Cursor(g, 0, 0);
    }
    public void drawRectangle(int width, int height){
        g.setColor(c);
        new Rectangle(g, xPos, yPos, width, height, fill);
        view.repaint();
    }

    public void drawTriangle(int size){
        g.setColor(c);
        new Triangle(g, xPos, yPos, size, fill);
        view.repaint();
    }

    public void drawOval(int size){
        g.setColor(c);
        new Oval(g, xPos, yPos, size, fill);
        view.repaint();
    }

    public void drawLine(int xTo, int yTo){
        g.setColor(c);
        new Line(g, xPos, yPos, xTo, yTo);
        cursor.moveCursor(g, xPos, yPos, xTo, yTo);
        xPos = xTo; yPos = yTo;
        view.repaint();
    }

    @Override
    public void setFill(String fill) {
        g.setColor(c);
        if(fill.equals("on")){
            this.fill = true;
        }else if(fill.equals("off")){
            this.fill = false;
        }
    }

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
            case "greeen":
                c = Color.green;
                break;
        }
    }
    @Override
    public void clear() {
        g.clearRect(0,0,550,550);
        cursor.moveCursor(g, xPos, yPos, xPos, yPos);
        view.repaint();
    }

    @Override
    public void reset() {
        cursor.moveCursor(g, xPos, yPos, 0, 0);
        xPos = 0; yPos = 0;
    }

    @Override
    public void moveTo(int x, int y) {
        cursor.moveCursor(g, xPos, yPos, x, y);
        view.repaint();
        xPos = x; yPos = y;
    }
}