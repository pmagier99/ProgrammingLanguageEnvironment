import java.util.LinkedList;

/**
 * A public class that extends ProgrammingCommands class
 * This is used to create methods in program environment
 */
public class Method extends ProgrammingCommands{

    String name;

    /**
     * Constructor that requires to provide name of the method
     * that is executed once called.
     * @param name the name for method
     */
    public Method(String name){
        this.name = name;
        commands = new LinkedList<String>();
    }

    @Override public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Method other = (Method) obj;
        if(name == null){
            if(other.name != null)
                return false;
        }else if(!name.equals(other.name))
            return false;
        return true;
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }



}
