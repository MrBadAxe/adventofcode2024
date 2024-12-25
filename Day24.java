import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Day24{
    private static ArrayList<String> filterSources(List<String> input){
        ArrayList<String> sources = new ArrayList<>();
        int k=0;
        while(!input.get(k).isEmpty()){
            sources.add(input.get(k));
            k++;
        }
        return sources;
    }
    private static ArrayList<String> filterGates(List<String> input){
        ArrayList<String> gates = new ArrayList<>();
        int k=0;
        while(!input.get(k).isEmpty()){
            k++;
        }
        k++;
        for(;k<input.size();k++){
            gates.add(input.get(k));
        }
        return gates;
    }
    private static LogicCircuit generateLogicCircuit(List<String> sources, List<String> gates){
        LogicCircuit circuit = new LogicCircuit();
        for(String source : sources){
            String[] split = source.split(":");
            String label = split[0];
            boolean signal = split[1].strip().equals("1");
            circuit.addSource(label,new StaticSource(label, signal));
        }
        
        while(gates.size() > 0){
            String gate = gates.removeFirst();
            String[] split = gate.split("\s+");
            String label = split[4];
            String operand1 = split[0];
            String operand2 = split[2];
            String operator = split[1];
            if(circuit.containsSource(operand1) && circuit.containsSource(operand2)){
                LogicSource source1 = circuit.getSource(operand1);
                LogicSource source2 = circuit.getSource(operand2);
                if(source1.getBranchWeight() > source2.getBranchWeight()){
                    LogicSource swap = source1;
                    source1 = source2;
                    source2 = swap;
                }
                if(operator.equals("AND")){
                    circuit.addSource(label, new AndGate(label, source1, source2));
                }else if(operator.equals("OR")){
                    circuit.addSource(label, new OrGate(label, source1, source2));
                }else if(operator.equals("XOR")){
                    circuit.addSource(label, new XorGate(label, source1, source2));
                }
            }else{
                gates.addLast(gate);
            }
        }
        return circuit;
    }
    private static ArrayList<String> processSwaps(ArrayList<String> gates, HashMap<String,String> swaps){
        for(int k=0;k<gates.size();k++){
            String gate = gates.get(k);
            String key = gate.split("\s+")[4];
            if(swaps.containsKey(key)){
                gates.set(k,gates.get(k).replace(key,swaps.get(key)));
            }
        }
        return gates;
    }
    public static String getPart01(List<String> input){
        ArrayList<String> sources = filterSources(input);
        ArrayList<String> gates = filterGates(input);
        LogicCircuit circuit = generateLogicCircuit(sources,gates);
        return Long.toString(circuit.getDataBus("z"));
    }
    public static String getPart02(List<String> input){
        HashMap<String,String> swaps = new HashMap<>();
        swaps.put("z12","kwb");
        swaps.put("kwb","z12");

        swaps.put("z16","qkf");
        swaps.put("qkf","z16");

        swaps.put("tgr","z24");
        swaps.put("z24","tgr");

        swaps.put("jqn","cph");
        swaps.put("cph","jqn");

        //cph,jqn,kwb,qkf,tgr,z12,z16,z24

        ArrayList<String> sources = filterSources(input);
        ArrayList<String> gates = filterGates(input);
        gates = processSwaps(gates, swaps);
        LogicCircuit circuit = generateLogicCircuit(sources,gates);

        System.out.println(circuit.getDataBus("z"));

        circuit.findSwaps();
        return "";
    }

}