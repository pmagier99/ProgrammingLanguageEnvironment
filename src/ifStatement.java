import java.util.LinkedList;
public class ifStatement extends ProgrammingCommands{

    public ifStatement(LinkedList<String> commands){
        this.commands = commands;
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
