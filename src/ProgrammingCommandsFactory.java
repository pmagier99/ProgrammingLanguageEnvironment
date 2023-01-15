import java.util.LinkedList;

/**
 * Factory class that returns Classes related to programming commands,
 * those are Loop, If Statement and Method.
 */

public class ProgrammingCommandsFactory {

    /**
     * Public function that return one of the Programming Commands classes
     * @param type String of type required programming command
     * @param name String of name for Method command, in other cases it may be empty string.
     * @return ifStatement/Method/Loop
     */
    public ProgrammingCommands getProgrammingCommands(String type, String name){
        if(type == null){
            return null;
        }

        if(type.equalsIgnoreCase("IFSTATEMENT")){
            return new ifStatement(new LinkedList<>());

        }else if(type.equalsIgnoreCase("METHOD")){
            return new Method(name);

        }else if(type.equalsIgnoreCase("LOOP")){
            return new Loop(new LinkedList<>());
        }


        return null;
    }

}
