public class Variable {

    String name;
    String value;

    public Variable(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}