import javax.swing.*;

public class Main {
    public static GUI gui = new GUI();
    static MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Programming Language Environment");

        mainFrame.add(gui);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}