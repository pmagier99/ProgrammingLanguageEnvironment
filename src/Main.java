import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Programming Language Environment");

        mainFrame.add(new GUI());

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}