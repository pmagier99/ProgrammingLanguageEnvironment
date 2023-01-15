import java.util.LinkedList;

/**
 * A public class that extends ProgrammingCommands class
 * This is used to create a loop in program environment
 */
public class Loop extends ProgrammingCommands{


    /**
     * Constructor that required providing List of commands
     * that are executed later.
     * @param commands new list of commands
     */
    public Loop(LinkedList<String> commands){
        this.commands = commands;
    }

}
