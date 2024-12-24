import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Day24{
    private static HashMap<String,Boolean> generateSources(List<String> input){
        HashMap<String,Boolean> sources = new HashMap<>();
        int k=0;
        String line = input.get(k);
        while(!line.isEmpty()){
            String[] split = line.split(":");
            String label = split[0];
            boolean signal = split[1].strip().equals("1");
            sources.putIfAbsent(label,signal);
            k++;
            line = input.get(k);
        }
        return sources;
    }
    private static long getDataBus(HashMap<String,Boolean> sources, String label){
        long output = 0L;
        for(int k=0;sources.containsKey(label+String.format("%02d",k));k++){
            long bitN = sources.get(label+String.format("%02d",k)) ? 1 : 0;
            output += bitN << k;
        }
        return output;
    }
    public static String getPart01(List<String> input){
        HashMap<String,Boolean> sources = generateSources(input);
        ArrayList<String> gates = new ArrayList<>();
        for(int k=sources.size()+1;k<input.size();k++){
            gates.add(input.get(k));
        }
        while(gates.size() > 0){
            String gate = gates.removeFirst();
            String[] split = gate.split("\s+");
            String label = split[4];
            String operand1 = split[0];
            String operand2 = split[2];
            String operator = split[1];
            if(sources.containsKey(operand1) && sources.containsKey(operand2)){
                if(operator.equals("AND")){
                    sources.put(label, sources.get(operand1) && sources.get(operand2));
                }else if(operator.equals("OR")){
                    sources.put(label, sources.get(operand1) || sources.get(operand2));
                }else if(operator.equals("XOR")){
                    sources.put(label, sources.get(operand1) ^ sources.get(operand2));
                }
            }else{
                gates.addLast(gate);
            }
        }
        return Long.toString(getDataBus(sources,"z"));
    }
    public static String getPart02(List<String> input){
        return "";
    }

}