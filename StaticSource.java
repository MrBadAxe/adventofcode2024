public class StaticSource implements LogicSource{
    private String label;
    private boolean output;

    public StaticSource(String label, boolean source){
        this.label = label;
        this.output = source;
    }
    public String getLabel(){
        return label;
    }
    public boolean getOutput(){
        return output;
    }
    public String toString(){
        return this.getLabel();
    }
}