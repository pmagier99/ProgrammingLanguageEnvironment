import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main {
    public static GUI gui = new GUI();
    static MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    static Parser parser = new Parser(canvas);
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Programming Language Environment");

        mainFrame.add(gui);
        gui.singleCommandField.addActionListener(e -> {

            if(Objects.equals(gui.singleCommandField.getText(), "run")){
                try {
                    parser.parseMultiCommands(gui.multipleCommandsField.getText());
                } catch (ApplicationException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                try {
                    parser.parseCommand(gui.singleCommandField.getText());
                } catch (ApplicationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        gui.runButton.addActionListener(e -> {
            try {
                parser.parseMultiCommands(gui.multipleCommandsField.getText());
            } catch (ApplicationException ex) {
                throw new RuntimeException(ex);
            }
        });

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}