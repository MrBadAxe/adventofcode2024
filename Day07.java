import java.util.List;
import java.util.ArrayList;

public class Day07{
    private static boolean testCalibration(long testValue, ArrayList<Long> operands){
        if(operands.size() == 2){
            return (testValue == operands.get(0) * operands.get(1) || testValue == operands.get(0) + operands.get(1));
        }else{
            ArrayList<Long> added = new ArrayList<>();
            added.addAll(operands);
            long op1 = added.removeFirst();
            long op2 = added.removeFirst();
            added.add(0,op1+op2);

            ArrayList<Long> multiplied = new ArrayList<>();
            multiplied.addAll(operands);
            op1 = multiplied.removeFirst();
            op2 = multiplied.removeFirst();
            multiplied.add(0,op1*op2);

            return testCalibration(testValue, added) || testCalibration(testValue, multiplied);
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
            if(testCalibration(testValue,operands)){
                total += testValue;
            }
        }
        return Long.toString(total);
    }
}