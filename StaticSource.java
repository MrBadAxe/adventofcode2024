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
    public void setLabel(String newLabel){
        this.label = newLabel;
    }
    public boolean getOutput(){
        return output;
    }
    public String toString(){
        return this.getLabel();
    }
    public int getBranchWeight(){
        return 1;
    }
    public boolean needsSwap(){
        return false;
    }
}