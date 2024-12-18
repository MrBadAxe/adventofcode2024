import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17{
    private static HashMap<String,Long> initializeRegisters(List<String> input){
        HashMap<String,Long> registers = new HashMap<>();
        Pattern descRegister = Pattern.compile("Register ([A-C]): (\\d+)");
        for(int k=0;k<3;k++){
            Matcher m = descRegister.matcher(input.get(k));
            m.find();
            registers.put(m.group(1),Long.parseLong(m.group(2)));
        }
        return registers;
    }
    private static HashMap<String,Long> initializeRegisters(long a, long b, long c){
        HashMap<String,Long> registers = new HashMap<>();
        registers.put("A",a);
        registers.put("B",b);
        registers.put("C",c);
        return registers;
    }
    private static int[] initializeProgram(String program){
        Pattern descProgram = Pattern.compile("Program: (\\d+(,\\d+)*)");
        Matcher m = descProgram.matcher(program);
        m.find();
        String[] instructions = m.group(1).split(",");
        int[] z = new int[instructions.length];
        for(int k=0;k<z.length;k++){
            z[k] = Integer.parseInt(instructions[k]);
        }
        return z;
    }
    private static long comboOperand(HashMap<String,Long> registers, int operand){
        long z = 0;
        switch(operand){
            case 0:
            case 1:
            case 2:
            case 3:
                z = operand;
                break;
            case 4: z = registers.get("A"); break;
            case 5: z = registers.get("B"); break;
            case 6: z = registers.get("C"); break;
        }
        return z;
    }
    private static ArrayList<Long> runProgram(HashMap<String,Long> registers, int[] program){
        ArrayList<Long> output = new ArrayList<>();
        int programCounter = 0;
        while(programCounter < program.length){
            long op1 = 0;
            long op2 = 0;
            long result = 0;
            switch(program[programCounter]){
                case 0:
                    op1 = registers.get("A");
                    op2 = (long)Math.pow(2L,comboOperand(registers, program[programCounter+1]));
                    result = Math.floorDiv(op1,op2);
                    registers.put("A",result);
                    programCounter += 2;
                    break;
                case 1:
                    op1 = registers.get("B");
                    op2 = program[programCounter+1];
                    result = op1 ^ op2;
                    registers.put("B",result);
                    programCounter += 2;
                    break;
                case 2:
                    op1 = comboOperand(registers, program[programCounter+1]);
                    result = op1 % 8;
                    registers.put("B",result);
                    programCounter += 2;
                    break;
                case 3:
                    op1 = registers.get("A");
                    if(op1 != 0){
                        programCounter = program[programCounter+1];
                    }else{
                        programCounter += 2;
                    }
                    break;
                case 4:
                    op1 = registers.get("B");
                    op2 = registers.get("C");
                    result = op1 ^ op2;
                    registers.put("B",result);
                    programCounter += 2;
                    break;
                case 5:
                    op1 = comboOperand(registers, program[programCounter+1]);
                    result = op1 % 8;
                    output.add(result);
                    programCounter += 2;
                    break;
                case 6:
                    op1 = registers.get("A");
                    op2 = (int)Math.pow(2,comboOperand(registers, program[programCounter+1]));
                    result = Math.floorDiv(op1,op2);
                    registers.put("B",result);
                    programCounter += 2;
                    break;
                case 7:
                    op1 = registers.get("A");
                    op2 = (int)Math.pow(2,comboOperand(registers, program[programCounter+1]));
                    result = Math.floorDiv(op1,op2);
                    registers.put("C",result);
                    programCounter += 2;
                    break;
                default:
            }
        }
        return output;
    }
    public static String getPart01(List<String> input){
        HashMap<String,Long> registers = initializeRegisters(input);
        int[] program = initializeProgram(input.get(4));
        ArrayList<Long> output = runProgram(registers, program);
        StringJoiner sjOutput = new StringJoiner(",");
        for(Long l : output){
            sjOutput.add(Long.toString(l));
        }
        return sjOutput.toString();
    }
    public static String getPart02(List<String> input){
        int[] program = initializeProgram(input.get(4));
        StringJoiner sjProgram = new StringJoiner(",");
        for(int i=0;i<program.length;i++){
            sjProgram.add(Integer.toString(program[i]));
        }
        String strProgram = sjProgram.toString();

        String strOutput = "";
        ArrayList<Long> testValues = new ArrayList<>();
        for(long k=1;k<=7;k++){
            testValues.add(k);
        }

        long testValue = 0;
        while(!strOutput.equals(strProgram)){
            testValue = testValues.removeFirst();
            HashMap<String,Long> registers = initializeRegisters(testValue, 0, 0);
            ArrayList<Long> output = runProgram(registers, program);
            StringJoiner sjOutput = new StringJoiner(",");
            for(Long l : output){
                sjOutput.add(Long.toString(l));
            }
            strOutput = sjOutput.toString();
            if(strProgram.substring(strProgram.length() - strOutput.length()).equals(strOutput)){
                for(long k=0;k<8;k++){
                    testValues.add(testValue * 8 + k);
                }
            }
        }
        return Long.toString(testValue);
    }

}