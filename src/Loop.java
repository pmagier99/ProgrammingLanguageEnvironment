import java.util.LinkedList;
public class Loop {

    LinkedList<String> commands;

    public Loop(LinkedList<String> commands){
        this.commands = commands;
    }

    public void addCommand(String command){
        commands.add(command);
    }

    public String toString(){
        StringBuilder string = new StringBuilder();

        for (String command : commands) {
            string.append(command).append("\n");
        }

        return string.toString();
    }


}
