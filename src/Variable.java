public class Variable {

    String name;
    String value;

    public Variable(String name){
        this.name = name;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public Variable updateVariable(String operator, String value){
        switch(operator){
            case "+":
                this.value = Integer.toString((Integer.parseInt(this.value) + Integer.parseInt(value)));
                break;
            case "-":
                this.value = Integer.toString((Integer.parseInt(this.value) - Integer.parseInt(value)));
                break;
            case "/":
                this.value = Integer.toString((Integer.parseInt(this.value) / Integer.parseInt(value)));
                break;
            case "*":
                this.value = Integer.toString((Integer.parseInt(this.value) * Integer.parseInt(value)));
                break;
            default:
                this.value = value;
        }
        return this;
    }

    @Override
    public String toString(){
        return "Variable [variableName=" + name + ", variableValue=  " + value + "]";
    }

    @Override public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Variable other = (Variable) obj;
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