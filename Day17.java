import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17{
    private static HashMap<String,Integer> initializeRegisters(List<String> input){
        HashMap<String,Integer> registers = new HashMap<>();
        Pattern descRegister = Pattern.compile("Register ([A-C]): (\\d+)");
        for(int k=0;k<3;k++){
            Matcher m = descRegister.matcher(input.get(k));
            m.find();
            registers.put(m.group(1),Integer.parseInt(m.group(2)));
        }
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
    private static int comboOperand(HashMap<String,Integer> registers, int operand){
        int z = 0;
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
    private static ArrayList<Integer> runProgram(HashMap<String,Integer> registers, int[] program){
        ArrayList<Integer> output = new ArrayList<>();
        int programCounter = 0;
        while(programCounter < program.length){
            int op1 = 0;
            int op2 = 0;
            int result = 0;
            switch(program[programCounter]){
                case 0:
                    op1 = registers.get("A");
                    op2 = (int)Math.pow(2,comboOperand(registers, program[programCounter+1]));
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
            System.out.println(registers);
        }
        return output;
    }
    public static String getPart01(List<String> input){
        HashMap<String,Integer> registers = initializeRegisters(input);
        int[] program = initializeProgram(input.get(4));
        ArrayList<Integer> output = runProgram(registers, program);
        StringJoiner sjOutput = new StringJoiner(",");
        for(Integer i : output){
            sjOutput.add(Integer.toString(i));
        }
        return sjOutput.toString();
    }
    public static String getPart02(List<String> input){
        return "";
    }

}