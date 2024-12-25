import java.util.List;
import java.util.ArrayList;

public class Day24{
    private static LogicCircuit generateLogicCircuit(List<String> input){
        LogicCircuit circuit = new LogicCircuit();
        int k=0;
        String line = input.get(k);
        while(!line.isEmpty()){
            String[] split = line.split(":");
            String label = split[0];
            boolean signal = split[1].strip().equals("1");
            circuit.addSource(label,new StaticSource(label, signal));
            k++;
            line = input.get(k);
        }
        k++;
        ArrayList<String> gates = new ArrayList<>();
        for( ;k<input.size();k++){
            gates.add(input.get(k));
        }
        while(gates.size() > 0){
            String gate = gates.removeFirst();
            String[] split = gate.split("\s+");
            String label = split[4];
            String operand1 = split[0];
            String operand2 = split[2];
            String operator = split[1];
            if(circuit.containsSource(operand1) && circuit.containsSource(operand2)){
                if(operator.equals("AND")){
                    circuit.addSource(label, new AndGate(label, circuit.getSource(operand1),circuit.getSource(operand2)));
                }else if(operator.equals("OR")){
                    circuit.addSource(label, new OrGate(label, circuit.getSource(operand1),circuit.getSource(operand2)));
                }else if(operator.equals("XOR")){
                    circuit.addSource(label, new XorGate(label, circuit.getSource(operand1),circuit.getSource(operand2)));
                }
            }else{
                gates.addLast(gate);
            }
        }
        return circuit;
    }
    public static String getPart01(List<String> input){
        
        LogicCircuit circuit = generateLogicCircuit(input);
        return Long.toString(circuit.getDataBus("z"));
    }
    public static String getPart02(List<String> input){
        return "";
    }

}