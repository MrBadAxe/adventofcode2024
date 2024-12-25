public class AndGate implements LogicSource{
    private String label;
    private LogicSource input1;
    private LogicSource input2;

    public AndGate(String label, LogicSource p, LogicSource q){
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
        return this.label;
    }
    public void setLabel(String newLabel){
        this.label = newLabel;
    }
    public boolean getOutput(){
        return (input1.getOutput() && input2.getOutput());
    }
    public String toString(){
        return this.getLabel() + "(" + this.getBranchWeight() + "):(" + input1.toString() + " AND " + input2.toString() + ")";
    }
    public int getBranchWeight(){
        return input1.getBranchWeight() + input2.getBranchWeight();
    }
    public boolean isCarryFlag(){
        return (input1 instanceof StaticSource && input1 instanceof StaticSource);
    }
    public boolean needsSwap(){
        if(this.label.charAt(0) == 'z'){ return true; }
        if(isCarryFlag()){ return false; }
        
        if(input1 instanceof XorGate && ((XorGate)input1).isHalfAdder() && input2 instanceof OrGate){ return false;}
        if(input2 instanceof XorGate && ((XorGate)input2).isHalfAdder() && input1 instanceof OrGate){ return false;}

        if(input1 instanceof XorGate && input2 instanceof AndGate && ((AndGate)input2).isCarryFlag()){ return false; }
        if(input2 instanceof XorGate && input1 instanceof AndGate && ((AndGate)input1).isCarryFlag()){ return false; }
        return true;
    }
}