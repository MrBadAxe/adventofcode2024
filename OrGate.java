public class OrGate implements LogicSource{
    private String label;
    private LogicSource input1;
    private LogicSource input2;

    public OrGate(String label, LogicSource p, LogicSource q){
        this.label = label;
        if(q instanceof XorGate && p instanceof AndGate){
            this.input1 = q;
            this.input2 = p;    
        }else{
            this.input1 = p;
            this.input2 = q;
        }
    }
    public String getLabel(){
        return this.label;
    }
    public boolean getOutput(){
        return (input1.getOutput() || input2.getOutput());
    }
    public String toString(){
        return this.getLabel() + ":(" + input1.toString() + " OR " + input2.toString() + ")";
    }
}