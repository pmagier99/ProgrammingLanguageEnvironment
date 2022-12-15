import java.util.LinkedList;
public class ifStatement {

    /**
     * A public class that allows to create own if Statement in the program.
     * It takes list of commands that needs to be executed, in order to create a class
     *
     */

    LinkedList<String> commands;

    public ifStatement(LinkedList<String> commands){
        this.commands = commands;
    }

    /**
     * Public function that adds <bold>String command</bold> to List of commands
     * @param command - single line command
     */
    public void addCommand(String command){
        commands.add(command);
    }

    /**
     * Public function that builds a new string from List of commands
     * @return the concatenated strings of commands
     */
    public String toString(){
        StringBuilder string = new StringBuilder();

        for (String command : commands) {
            string.append(command).append("\n");
        }

        return string.toString();
    }

    /**
     * Public function that return true or false, depending on provided values and operator
     * @param parameterOne - first parameter taken to comparison
     * @param parameterTwo - second parameter taken to comparison
     * @param operator - operator that defines the result of comparison
     * @return true/false
     */
    public boolean checkCondition(int parameterOne, int parameterTwo, String operator){
        switch(operator){
            case "<":
                return parameterOne < parameterTwo;
            case "<=":
                return parameterOne <= parameterTwo;
            case ">=":
                return parameterOne >= parameterTwo;
            case ">":
                return parameterOne > parameterTwo;
            case "==":
                return parameterOne == parameterTwo;
            default:
                return false;
        }
    }


}
