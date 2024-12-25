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
    public void setLabel(String newLabel){
        this.label = newLabel;
    }
    public boolean getOutput(){
        return (input1.getOutput() ^ input2.getOutput());
    }
    public String toString(){
        return this.getLabel() + "(" + this.getBranchWeight() + "):(" + input1.toString() + " XOR " + input2.toString() + ")";
    }
    public int getBranchWeight(){
        return input1.getBranchWeight() + input2.getBranchWeight();
    }
    public boolean isHalfAdder(){
        char input1prefix = input1.getLabel().charAt(0);
        char input2prefix = input2.getLabel().charAt(0);
        return (input1prefix == 'x' && input2prefix == 'y') || (input1prefix == 'y' && input2prefix == 'x');
    }
    private boolean isOutputLine(){
        char outputprefix = label.charAt(0);
        return outputprefix == 'z';
    }
    public boolean needsSwap(){
        if(isOutputLine()){ return false; }
        if(isHalfAdder()){ return false; }
        return true;
    }
}