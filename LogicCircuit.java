import java.util.HashMap;

public class LogicCircuit{
    HashMap<String,LogicSource> sources;

    public LogicCircuit(){
        sources = new HashMap<>();
    }

    public void addSource(String label, LogicSource source){
        sources.put(label,source);
    }
    public LogicSource getSource(String label){
        return sources.get(label);
    }
    public boolean containsSource(String label){
        return sources.containsKey(label);
    }
    public void swap(String label1, String label2){
        LogicSource source1 = sources.get(label1);
        LogicSource source2 = sources.get(label2);
        source1.setLabel(label2);
        source2.setLabel(label1);
        sources.put(label1, source2);
        sources.put(label2, source1);
    }
    public void findSwaps(){
        for(String key : sources.keySet()){
            LogicSource source = sources.get(key);
            if(source.needsSwap()){
                System.out.println(key + "|" + source.getClass() + "|" + source.getBranchWeight() + "|" + source.toString() + "\n");
            }
        }
    }
    public long getDataBus(String prefix){
        long output = 0L;
        int index = 0;
        String label = prefix + String.format("%02d",0);
        while(sources.containsKey(label)){
            LogicSource source = sources.get(label);
            System.out.println(source.getLabel() + ": " + source.getBranchWeight());
            long bitN = source.getOutput() ? 1 : 0;
            output += bitN << index;

            index++;
            label = prefix + String.format("%02d",index);
        }
        return output;
    }

}