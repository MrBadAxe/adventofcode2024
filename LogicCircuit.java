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
    public long getDataBus(String prefix){
        long output = 0L;
        int index = 0;
        String label = prefix + String.format("%02d",0);
        while(sources.containsKey(label)){
            LogicSource source = sources.get(label);
            //System.out.println(source);
            long bitN = source.getOutput() ? 1 : 0;
            output += bitN << index;

            index++;
            label = prefix + String.format("%02d",index);
        }
        return output;
    }

}