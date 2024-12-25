public class XorGate implements LogicSource{
    private String label;
    private LogicSource input1;
    private LogicSource input2;

    public XorGate(String label, LogicSource p, LogicSource q){
        this.label = label;
        if(p instanceof StaticSource && q instanceof StaticSource && p.getLabel().charAt(0) == 'y'){
            this.input1 = q;
            this.input2 = p;    
        }else if(p instanceof XorGate && q instanceof AndGate){
            this.input1 = q;
            this.input2 = p;    
        }else{
            this.input1 = p;
            this.input2 = q;
        }
    }
    public String getLabel(){
        return label;
    }
    public boolean getOutput(){
        return (input1.getOutput() ^ input2.getOutput());
    }
    public String toString(){
        return this.getLabel() + ":(" + input1.toString() + " XOR " + input2.toString() + ")";
    }
}