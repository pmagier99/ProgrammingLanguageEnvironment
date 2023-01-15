import java.util.LinkedList;

/**
 * A public class that is used to check syntax of all lines before the program run,
 * if there are any errors, those are printed to the output screen.
 */

public class SyntaxChecker {

    MyCanvas canvas;
    GUI gui;
    Parser parser;
    LinkedList<String> errors;

    /**
     * Constructor that requires to provide MyCanvas and GUI class.
     * @param canvas - MyCanvas class to create new parser class and clear/reset canvas.
     * @param gui - GUI class to create new parser and display errors.
     */
    public SyntaxChecker(MyCanvas canvas, GUI gui){
        this.canvas = canvas;
        this.gui = gui;
        parser = new Parser(canvas,gui);
        errors = new LinkedList<>();
    }

    /**
     * Public function that checks for any potential errors in the code.
     * @param multiCommands list of commands that needs to be checked.
     * @return true if there are no errors, false if there are.
     */
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

        if(errors.size() == 0){ //if there are no errors reset and clear canvas.
            canvas.reset(); canvas.clear();
            return true;
        }else{//otherwise print out all errors.
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
