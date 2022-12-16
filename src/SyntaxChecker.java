import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SyntaxChecker {

    MyCanvas canvas;
    GUI gui;
    Parser parser;
    LinkedList<String> errors;

    public SyntaxChecker(MyCanvas canvas, GUI gui){
        this.canvas = canvas;
        this.gui = gui;
        parser = new Parser(canvas, gui);
        errors = new LinkedList<>();
    }

    public boolean check(String multiCommands){
        String[] commands = multiCommands.split("\\r?\\n");

        for(String c : commands){
            try{
                parser.currentLine++;
                parser.parseCommand(c);
                canvas.clear();
            } catch (ApplicationException e) {
                errors.add(gui.errorMessage.getText());
            }
        }

        if(errors.size() == 0){
            canvas.reset(); canvas.clear();
            return true;
        }else{
            for (int i = 0; i < errors.size(); i++) {
                gui.errorMessage.setText("");
                canvas.reset();
                canvas.xPos = 20; canvas.yPos = 20*(i+1);
                canvas.printError(errors.get(i));

            }
            return false;
        }

    }

}
