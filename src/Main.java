import javax.swing.*;
import java.util.Objects;


public class Main {
    public static GUI gui = new GUI();
    static MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    static Parser parser = new Parser(canvas, gui);
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Programming Language Environment");

        mainFrame.add(gui);

        gui.singleCommandField.addActionListener(e -> runSingleLine());

        gui.runButton.addActionListener(e -> runMultiLine());

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    private static void runSingleLine(){
        if(Objects.equals(gui.singleCommandField.getText(), "run")){
            try {
                parser.parseMultiCommands(gui.multipleCommandsField.getText());
            } catch (ApplicationException ex) {
                throw new RuntimeException(ex);
            }

        }else if(Objects.equals(gui.singleCommandField.getText(), "save")){
            new CreateFile(gui.multipleCommandsField.getText());

        }else if(Objects.equals(gui.singleCommandField.getText(), "load")){
            LoadFile lf = new LoadFile();
            gui.multipleCommandsField.setText(lf.printText());

        }else{
            try {
                parser.parseCommand(gui.singleCommandField.getText());
                gui.singleCommandField.setText("");
            } catch (ApplicationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private static void runMultiLine(){
        try {
            parser.parseMultiCommands(gui.multipleCommandsField.getText());
        } catch (ApplicationException ex) {
            throw new RuntimeException(ex);
        }
    }
}