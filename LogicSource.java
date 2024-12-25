public interface LogicSource{
    public String getLabel();
    public void setLabel(String newLabel);
    public boolean getOutput();
    public int getBranchWeight();
    public boolean needsSwap();
}