import java.util.List;
import java.util.ArrayList;

public class Day07{
    private static long concat(long a, long b){
        return Long.parseLong(Long.toString(a) + "" + Long.toString(b));
    }
    private static boolean testCalibration(long testValue, ArrayList<Long> operands, boolean useConcat){
        if(operands.size() == 2){
            if(testValue == operands.get(0) * operands.get(1)){
                return true;
            }else if(testValue == operands.get(0) + operands.get(1)){
                return true;
            }else if(useConcat && testValue == concat(operands.get(0),operands.get(1))){
                return true;
            }else return false;
        }else{
            long op1 = operands.removeFirst();
            long op2 = operands.removeFirst();

            ArrayList<Long> added = new ArrayList<>();
            added.addAll(operands);
            added.add(0,op1+op2);
            if(testCalibration(testValue,added,useConcat)){ return true; }

            ArrayList<Long> multiplied = new ArrayList<>();
            multiplied.addAll(operands);
            multiplied.add(0,op1*op2);
            if(testCalibration(testValue,multiplied,useConcat)){ return true; }

            if(useConcat){
                ArrayList<Long> concatenated = new ArrayList<>();
                concatenated.addAll(operands);
                concatenated.add(0,concat(op1,op2));
                if(testCalibration(testValue,concatenated,useConcat)){ return true; }    
            }
            return false;
        }
    }

    public static String getPart01(List<String> input){
        long total = 0;
        for(String line : input){
            String[] split = line.split(":");
            long testValue = Long.parseLong(split[0]);
            String[] split2 = split[1].split("\s+");
            ArrayList<Long> operands = new ArrayList<>();
            for(int k=1;k<split2.length;k++){
                operands.add(Long.parseLong(split2[k]));
            }
            if(testCalibration(testValue,operands,false)){
                total += testValue;
            }
        }
        return Long.toString(total);
    }
}