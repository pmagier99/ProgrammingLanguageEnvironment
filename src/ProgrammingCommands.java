import java.util.LinkedList;

abstract class ProgrammingCommands {

    /**
     * A public class that allows to create own if Statement in the program.
     * It takes list of commands that needs to be executed, in order to create a class
     *
     */
    LinkedList<String> commands;

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
}
