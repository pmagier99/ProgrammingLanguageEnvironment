import java.util.LinkedList;
/**
 * A public class that extends ProgrammingCommands class
 * This is used to create an if statement in program environment
 */
public class ifStatement extends ProgrammingCommands{

    /**
     * Constructor that required providing List of commands
     * that are executed later.
     * @param commands new list of commands
     */
    public ifStatement(LinkedList<String> commands){
        this.commands = commands;
    }




}
