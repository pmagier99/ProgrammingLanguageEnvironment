import java.util.*;

/**
 * A public class that reads the input from the user,
 * then it reads and split it into command and parameters,
 * and finally execute them.
 */
public class Parser{

    String command; //stores a command name
    MyCanvas canvas; //used to import all commands to draw
    GUI gui; //used to get the JLabel to display error messages
    LinkedList<Variable> vars = new LinkedList<>();
    LinkedList<String> params;



    /**
     * A class constructor.
     * @param canvas the canvas from which drawn and non-drawn are taken
     * @param gui for getting the JLabel to display error messages
     */
    public Parser(MyCanvas canvas, GUI gui){
        this.canvas = canvas;
        this.gui = gui;
    }

    /**
     * Reads a given command, splits it and then execute it.
     * @param fullCommand the user input from text-box
     * @throws ApplicationException used if given command does not exist
     */
    public void parseCommand(String fullCommand) throws ApplicationException {
        splitCommand(fullCommand);
        if(!Objects.equals(gui.errorMessage.getText(), "")) gui.errorMessage.setText(""); //clear error message after entering correct one

        switch (command){
            case "rect":
                checkIfParametersAreValid(true, 2);
                canvas.drawRectangle(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(1)));
                break;
            case "circle":
                checkIfParametersAreValid(true, 1);
                canvas.drawOval(Integer.parseInt(params.get(0)));
                break;
            case "triangle":
                checkIfParametersAreValid(true, 1);
                canvas.drawTriangle(Integer.parseInt(params.get(0)));
                break;
            case "drawto":
                checkIfParametersAreValid(true, 2);
                canvas.drawLine(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(0)));
                break;
            case "moveto":
                checkIfParametersAreValid(true, 2);
                canvas.moveTo(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(0)));
                break;
            case "reset":
                checkIfParametersAreValid(false, 0);
                canvas.reset();
                break;
            case "clear":
                checkIfParametersAreValid(false, 0);
                canvas.clear();
                break;
            case "fill":
                checkIfParametersAreValid(false, 1);
                canvas.setFill(params.get(0));
                break;
            case "pen":
                checkIfParametersAreValid(false, 1);
                canvas.setColour(params.get(0));
                break;
            case "var":
                checkNumberOfParameters(3, params.size());
                if(!checkIfVariableExists(params.get(0))){
                    vars.add(new Variable(params.get(0), params.get(2)));
                }else{
                    gui.errorMessage.setText("Error detected: This variable already exists");
                    throw new ApplicationException("Variable already exists");
                }
                break;
            default:

                if(vars.size() > 0){
                    for (Variable var : vars) {
                        if (Objects.equals(var.name, command)) {
                            var.value = params.get(1);
                        }
                    }
                }else{
                    gui.errorMessage.setText("Error detected: Entered command is not recognised");
                    throw new ApplicationException("Invalid command");
                }

        }

     }

    /**
     * Splits a command into command name and parameters
     * @param fullCommand the user input from text-box
     */
     private void splitCommand(String fullCommand) {
        params = new LinkedList<>();
        params.addAll(Arrays.asList(fullCommand.split(" ")));
        command = params.removeFirst();

     }

    /**
     * Reads a multiline textbox, splits all the line into commands,
     * and then execute them.
     * @param multiCommands the user input from big text-box
     */
     public void parseMultiCommands(String multiCommands) throws ApplicationException {
         String[] lines = multiCommands.split("\\r?\\n");
         for(String c : lines){
             parseCommand(c);
         }
     }

    /**
     * Checks if number of parameters is correct
     * @param parametersExpected the number of expected parameters
     * @param actualLength the actual number of paremeters
     * @throws ApplicationException used if the number of parameters is either not enough or too big.
     */
     private boolean checkNumberOfParameters(int parametersExpected, int actualLength) throws ApplicationException {
        if(parametersExpected > actualLength){
            gui.errorMessage.setText("Error detected: The number of entered parameters is not enough for this command");
            throw new ApplicationException("Not enough parameters");
        }
        if(parametersExpected < actualLength){
            gui.errorMessage.setText("Error detected: The number of entered parameters is too large for this command");
            throw new ApplicationException("Too many parameters");
        }
        return true;
     }

     private void checkIfParametersAreValid(boolean requireInt, int parametersExpected) throws ApplicationException {
         String first = params.getFirst();
         String last = params.getLast();


         if(checkNumberOfParameters(parametersExpected, params.size())){ //commands have correct amount of parameters;

             if(requireInt){ //commands needs integer

                if(!(first.matches(("[0-9]+")) && last.matches("[0-9]+"))){ //checks if none of parameter is number
                    if(vars.size() > 0){ //checks if there are any variables
                        for(int i = 0; i < vars.size(); i++){
                            if(!(Objects.equals(vars.get(i).name, first) && Objects.equals(vars.get(i).name, last))){ //checks if parameters is not variable
                                gui.errorMessage.setText("Error detected: Entered parameter is not recognised");
                                throw new ApplicationException("Invalid parameter detected");
                            }else{
                                params.set(i, vars.get(i).getValue());//changes the name of variable into its value;
                            }
                        }
                    }else{
                        gui.errorMessage.setText("Error detected: Entered parameter is not recognised");
                        throw new ApplicationException("Invalid parameter detected");
                    }
                }//command can be performed

             } else{ //commands does not need integer
                 if(first.matches(("[0-9]+")) && last.matches("[0-9]+")){
                     gui.errorMessage.setText("Error detected: Entered parameter is not recognised");
                     throw new ApplicationException("Invalid parameter detected");
                 }
             }
         }
     }

     private boolean checkIfVariableExists(String name){
         for(Variable var : vars){
             if(Objects.equals(var.name, name)) return true;
         }
         return false;
     }
}
