import java.awt.*;

abstract class Shapes {
    public void drawRectangle(Graphics g, int x, int y, int width, int height, boolean fill){

        if (fill) g.fillRect(x, y, width, height); else g.drawRect(x, y, width, height);
    }

    public void drawOval(Graphics g, int x, int y, int size, boolean fill){

        if(fill) g.fillOval(x, y, size, size); else g.drawOval(x, y, size, size);
    }

    public void drawLine(Graphics g, int xFrom, int yFrom, int xTo, int yTo){
        g.drawLine(xFrom, yFrom, xTo, yTo);
    }

    //x1 y1 - start point
    //x2 y2 - x+size y1
    //x3 y3 - x+size/2 y=(size*Math.sqrt(3))/2
    public void drawTriangle(Graphics g, int x, int y, int size, boolean fill){
        int[] xArray = {x, x+size, x+(size/2)};
        int[] yArray = {y, y, y - (int) Math.floor((size*Math.sqrt(3))/2)};

        if(fill) g.fillPolygon(xArray, yArray, 3); else g.drawPolygon(xArray, yArray, 3);
    }
}
