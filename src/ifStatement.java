import java.util.LinkedList;
public class ifStatement extends ProgrammingCommands{

    boolean flag = false;
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
    public boolean checkCondition(String parameterOne, String parameterTwo, String operator){

        if(parameterOne.matches(("[0-9]+")) && parameterTwo.matches(("[0-9]+"))){
            int paramOne = Integer.parseInt(parameterOne);
            int paramTwo = Integer.parseInt(parameterTwo);
            switch(operator){
                case "<":
                    return paramOne < paramTwo;
                case "<=":
                    return paramOne <= paramTwo;
                case ">=":
                    return paramOne >= paramTwo;
                case ">":
                    return paramOne > paramTwo;
                case "==":
                    return paramOne == paramTwo;
                default:
                    return false;
            }
        }else{
            return parameterOne.equals(parameterTwo);
        }



    }


}
