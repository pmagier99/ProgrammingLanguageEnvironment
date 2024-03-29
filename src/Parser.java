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
    int currentLine = 0; //used to keep track of lines
    HashMap<Integer, String> lines = new HashMap<>(); //stores all lines provided by the user
    List<Variable> vars = new ArrayList<>(); // stores all variables created
    ArrayList<String> params; // stores parameters of each command.
    List<Method> methods = new ArrayList<>(); //stores all methods created
    ProgrammingCommandsFactory pcf = new ProgrammingCommandsFactory();
    ProgrammingCommands pc;
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
            case "hexagon":
                checkIfParametersAreValid(true, 1);
                canvas.drawHexagon(Integer.parseInt(params.get(0)));
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
                if(checkIfVariableExists(params.get(0))){
                    gui.errorMessage.setText("Error detected: This variable already exists. Line:" + currentLine);
                    throw new ApplicationException("Variable already exists. Line:" + currentLine);
                }else{
                    Variable newVar = new Variable(params.get(0));
                    newVar.setValue(params.get(2));
                    vars.add(newVar);
                }
                break;
            case "while":
                pc = pcf.getProgrammingCommands("LOOP", "");
                for(int i = currentLine+1; i<lines.size(); i++){
                    if(Objects.equals(lines.get(i), "endloop")){
                        break;
                    }else{
                        pc.addCommand(lines.get(i));
                    }
                }

                if(pc.commands.size() != 0)
                    parseWhile(pc.toString(), params.get(0), params.get(1), params.get(2));

                break;
            case "if":
                pc = pcf.getProgrammingCommands("IFSTATEMENT", "");
                for(int i = currentLine+1; i<lines.size(); i++){
                    if(Objects.equals(lines.get(i), "endif")){
                        break;
                    }else{
                        pc.addCommand(lines.get(i));
                    }
                }
                parseIfStatement(params.get(0));
                break;
            case "method":
                if(checkIfMethodExists(params.get(0))){
                    gui.errorMessage.setText("Error detected: This method already exists. Line:" + currentLine);
                    throw new ApplicationException("Method already exists. Line:" + currentLine);
                }else{
                    pc = pcf.getProgrammingCommands("METHOD", params.get(0).toLowerCase());
                    methods.add((Method) pc);

                    //adding commads
                    for(int i = currentLine+1; i<lines.size(); i++){
                        if(Objects.equals(lines.get(i), "endmethod")){
                            break;
                        }else{
                            pc.addCommand(lines.get(i));
                        }
                    }
                    currentLine+=pc.commands.size()+1;
                    break;
                }
            case "endloop":
            case "endif":
            case "endmethod":
                break;
            default:
                if(checkIfVariableExists(command) ){ //update variable
                    int index = vars.indexOf(new Variable(command));
                    Variable var = vars.get(index);
                    vars.set(index, var.updateVariable(params.get(0), params.get(1)));

                }else if(checkIfMethodExists(command)){
                    int index = methods.indexOf(new Method(command));
                    pc = methods.get(index);

                    if(pc.commands.size() > 0)
                        parseMethod((Method) pc);

                }else if(params.size() == 2){ //create new variable
                    if(params.get(0) == "="){
                        Variable newVar = new Variable(params.get(0));
                        newVar.setValue(params.get(1));
                        vars.add(newVar);
                    }
                }else{
                    gui.errorMessage.setText("Error detected: Entered command is not recognised. Line:" + currentLine);
                    throw new ApplicationException("Invalid command. Line:" + currentLine);
                }

        }
     }

    /**
     * Splits a command into command name and parameters
     * @param fullCommand the user input from text-box
     */
     private void splitCommand(String fullCommand) {
        params = new ArrayList<>();
        params.addAll(Arrays.asList(fullCommand.split(" ")));
        command = params.remove(0).toLowerCase();

     }

    /**
     * Reads a multiline textbox, splits all the line into commands,
     * and then execute them.
     * @param multiCommands the user input from big text-box
     */
     public void parseMultiCommands(String multiCommands) throws ApplicationException {
         SyntaxChecker sc = new SyntaxChecker(canvas, gui);
         if(!sc.check(multiCommands)) return;

         String[] commands = multiCommands.split("\\r?\\n");
         for(int i = 0; i < commands.length; i++){
             lines.put(i, commands[i].toLowerCase());
         }

         currentLine = 0;
         while(currentLine < lines.size()){
             parseCommand(lines.get(currentLine));
             currentLine++;
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
            gui.errorMessage.setText("Error detected: The number of entered parameters is not enough for this command. Line:" + currentLine);
            throw new ApplicationException("Not enough parameters. Line:" + currentLine);
        }
        if(parametersExpected < actualLength){
            gui.errorMessage.setText("Error detected: The number of entered parameters is too large for this command. Line:" + currentLine);
            throw new ApplicationException("Too many parameters. Line:" + currentLine);
        }
        return true;
     }

    /**
     * Checks if all provided parameters match with the requirements of commands.
     * @param requireInt - boolean to decide if command requires number or not.
     * @param parametersExpected - number of expected parameters in command
     * @throws ApplicationException
     */
     private void checkIfParametersAreValid(boolean requireInt, int parametersExpected) throws ApplicationException {
         if(parametersExpected == 0) return;

         checkNumberOfParameters(parametersExpected, params.size());
         String first = params.get(0);
         String last = params.get(params.size()-1);



         //converts any variables to its values
         if(checkIfVariableExists(first)){
            for(Variable var : vars){
                if(Objects.equals(var.name, first)) params.set(0, var.value);
            }
         }

         if(checkIfVariableExists(last)){
             for(Variable var : vars){
                 if(Objects.equals(var.name, last)) params.set(params.size()-1, var.value);
             }
         }
         //end of converting

         //after converting, it assigns new values;
         first = params.get(0);
         last = params.get(params.size()-1);

         //does not require parameters and there is number parameter
         if(!requireInt && first.matches(("[0-9]+")) && last.matches("[0-9]+")){
             gui.errorMessage.setText("Error detected: Entered parameter is not recognised. Line:" + currentLine);
             throw new ApplicationException("Invalid parameter detected. Line:" + currentLine);
         }

         //require number and none of parameters are number
         if(requireInt && !first.matches(("[0-9]+")) && !last.matches("[0-9]+")){
             gui.errorMessage.setText("Error detected: Entered parameter is not recognised. Line:" + currentLine);
             throw new ApplicationException("Invalid parameter detected. Line:" + currentLine);
         }

     }

    /**
     * Private function that checks if provided String name is also a name of existed variable
     * @param name - name of Variable that needs to be checked for existence
     * @return true if exists, false if it does not
     */

     private boolean checkIfVariableExists(String name){

         for(Variable var : vars){
             if(Objects.equals(var.name, name)) return true;
         }
         return false;
     }

    /**
     * Private function that checks if provided String name is also a name of existed method.
     * @param name - name of Variable that needs to be checked for existence
     * @return true if exists, false if it does not
     */
    private boolean checkIfMethodExists(String name){

        for(Method m : methods){
            if(Objects.equals(m.name, name)) return true;
        }
        return false;
    }

    /**
     * Private function that return int value of variable with provided name,
     * @param varname - name of variable that needs to converted to integer
     * @return the Integer from variable.
     */
    private int toInt(String varname) throws ApplicationException {
         if(checkIfVariableExists(varname)){

             try{
                 return Integer.parseInt(vars.get(vars.indexOf(new Variable(varname))).value);
             }catch (NumberFormatException e){
                 gui.errorMessage.setText("Error detected: Entered parameter is not recognised. Line:" + currentLine);
                 throw new ApplicationException("Invalid parameter detected. Line:" + currentLine);
             }
         }

         return Integer.parseInt(varname);
    }

    /**
     * Private function to perform if Statement created by user in program.
     * @param condition - condition that needs to be true in order to perform provided commands
     * @throws ApplicationException
     */
    private void parseIfStatement(String condition) throws ApplicationException {
        String[] commands = pc.toString().split("\\r?\\n");
        String[] parameters = {"",""};
        int parametersIndex = 0;
        String operator = "";
        for(int i = 0; i < condition.length(); i++){
            char ch = condition.charAt(i);
                if(ch != '<' && ch != '>' && ch != '=' ){
                    parameters[parametersIndex] += ch;
                }else {
                    operator += ch;
                    if(operator.length() == 1) parametersIndex++;
                }
        }

        //converts parameters to int, even if they are variable
        String parameter1 = parameters[0];
        String parameter2 = parameters[1];

        if(checkIfVariableExists(parameter1))
            parameter1 = vars.get(vars.indexOf(new Variable(parameter1))).value;

        if(checkIfVariableExists(parameter2))
            parameter2 = vars.get(vars.indexOf(new Variable(parameter2))).value;


        if(parameter1.matches(("[0-9]+")) && !parameter2.matches(("[0-9]+"))){
            gui.errorMessage.setText("Error detected: Entered parameter is not recognised. Line:" + currentLine);
            throw new ApplicationException("Invalid parameter detected. Line:" + currentLine);
        }

        if(!parameter1.matches(("[0-9]+")) && parameter2.matches(("[0-9]+"))){
            gui.errorMessage.setText("Error detected: Entered parameter is not recognised. Line:" + currentLine);
            throw new ApplicationException("Invalid parameter detected. Line:" + currentLine);
        }


        if(pc.checkCondition(parameter1, parameter2, operator) && pc.commands.size() > 0){
            for(String c : commands) parseCommand(c);
        }
        currentLine+=pc.commands.size()+1;
    }

    /**
     * Private function that executes provided commands as many times as it is provided by the user in condition
     * @param multiCommands- list of commands that needs to be executed if condition is true
     * @param start - beginning of the loop
     * @param operator - to specify condition that needs to met to run loop
     * @param end - end of loop
     * @throws ApplicationException
     */
     private void parseWhile(String multiCommands, String start, String operator, String end) throws ApplicationException {
         String[] commands = multiCommands.split("\\r?\\n");
         int S = toInt(start);
         int E = toInt(end);

         switch (operator){
             case ">":
                 while(S > E){
                     for(String c : commands) parseCommand(c);
                     S = toInt(start);
                 }
                 break;
             case "<":
                 while(S < E){
                     for(String c : commands) parseCommand(c);
                     S = toInt(start);
                 }
                 break;
             case "<=":
                 while(S <= E){
                     for(String c : commands) parseCommand(c);
                     S = toInt(start);
                 }
                 break;
             case ">=":
                 while(S >= E){
                     for(String c : commands) parseCommand(c);
                     S = toInt(start);
                 }
                 break;
         }
         currentLine+=pc.commands.size()+1;
     }

    /**
     * Private function that execute commands that were passed to given method
     * @param method - Method that needs to be executed.
     * @throws ApplicationException
     */
     private void parseMethod(Method method) throws ApplicationException {
         String[] commands = method.toString().split("\\r?\\n");

         for(String c : commands){
            parseCommand(c);
         }
     }
}
