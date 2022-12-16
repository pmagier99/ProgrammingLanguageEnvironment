import java.util.LinkedList;
public class Method extends ProgrammingCommands{

    String name;
    LinkedList<String> params;

    public Method(String name){
        this.name = name;
        params = new LinkedList<>();
        commands = new LinkedList<>();
    }

    public void addParams(String param){
        params.add(param);
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
