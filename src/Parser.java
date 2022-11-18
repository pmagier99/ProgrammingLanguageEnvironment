/**
 * A public class that reads the input from the user,
 * then it reads and split it into command and parameters,
 * and finally execute them.
 */
public class Parser{

    String command; //stores a command name
    String[] nonNumbersParameters; //stores non number parameters
    int[] numbersParameters; //stores number parameters
    int actualLength; //used to check number of parameters
    MyCanvas canvas; //used to import all commands to draw

    /**
     * A class constructor.
     * @param canvas the canvas from which drawn and non-drawn are taken
     */
    public Parser(MyCanvas canvas){
        this.canvas = canvas;
    }

    /**
     * Reads a given command, splits it and then execute it.
     * @param fullCommand the user input from text-box
     * @throws ApplicationException used if given command does not exist
     */
    public void parseCommand(String fullCommand) throws ApplicationException {
        splitCommand(fullCommand);

        switch (command){
            case "rect":
                checkNumberOfParameters(2,actualLength);
                canvas.drawRectangle(numbersParameters[0], numbersParameters[1]);
                break;
            case "circle":
                checkNumberOfParameters(1,actualLength);
                canvas.drawOval(numbersParameters[0]);
                break;
            case "triangle":
                checkNumberOfParameters(1,actualLength);
                canvas.drawTriangle(numbersParameters[0]);
                break;
            case "drawto":
                checkNumberOfParameters(2,actualLength);
                canvas.drawLine(numbersParameters[0], numbersParameters[1]);
                break;
            case "moveto":
                checkNumberOfParameters(2,actualLength);
                canvas.moveTo(numbersParameters[0],numbersParameters[1]);
                break;
            case "reset":
                checkNumberOfParameters(0,actualLength);
                canvas.reset();
                break;
            case "clear":
                checkNumberOfParameters(0,actualLength);
                canvas.clear();
                break;
            case "fill":
                checkNumberOfParameters(1,actualLength);
                canvas.setFill(nonNumbersParameters[0]);
                break;
            case "pen":
                checkNumberOfParameters(1,actualLength);
                canvas.setColour(nonNumbersParameters[0]);
                break;
            default:
                throw new ApplicationException("Invalid command");
        }

     }

    /**
     * Splits a command into command name and parameters
     * @param fullCommand the user input from text-box
     * @throws ApplicationException used if any of the parameter is invalid
     */
     private void splitCommand(String fullCommand) throws ApplicationException {
        String[] strArray = fullCommand.split(" "); //split command into array by space
        command = strArray[0].toLowerCase(); //first element of array is command name
        actualLength = (strArray.length-1); //stores number of parameters entered


         if(command.equals("fill") || command.equals("pen")){
             nonNumbersParameters = new String[actualLength];
             for(int i = 1; i < strArray.length; i++){
                 nonNumbersParameters[i-1] = strArray[i];
                 if(strArray[i].matches("[0-9]+")) throw new ApplicationException("Invalid parameter detected");
             }

         }else{
             numbersParameters = new int[actualLength];
             for(int i = 1; i < strArray.length; i++){
                 try{
                     numbersParameters[i-1] = Integer.parseInt(strArray[i]);
                 }catch (Exception e){
                     throw new ApplicationException("Invalid parameter detected");
                 }
             }
         }
     }

    /**
     * Reads a multiline textbox, splits all the line into commands,
     * and then execute them.
     * @param multiCommands the user input from big text-box
     * @throws ApplicationException
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
     private void checkNumberOfParameters(int parametersExpected, int actualLength) throws ApplicationException{
        if(parametersExpected > actualLength) throw new ApplicationException("Not enough parameters");
        if(parametersExpected < actualLength) throw new ApplicationException("Too many parameters");
     }





}
