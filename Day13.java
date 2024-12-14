import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class Day13{
    private static ArrayList<ClawMachine> generateClawMachineList(List<String> input, boolean bignums){
        Pattern pBtnA = Pattern.compile("Button A: X\\+(\\d+),\s+Y\\+(\\d+)");
        Pattern pBtnB = Pattern.compile("Button B: X\\+(\\d+),\s+Y\\+(\\d+)");
        Pattern pPrize = Pattern.compile("Prize: X=(\\d+),\s+Y=(\\d+)");

        ArrayList<ClawMachine> machines = new ArrayList<>();
        for(int machineIndex = 0; machineIndex < input.size(); machineIndex += 4){
            Point pointA = parsePoint(pBtnA, input.get(machineIndex));
            Point pointB = parsePoint(pBtnB, input.get(machineIndex+1));
            Point pointPrize = parsePoint(pPrize, input.get(machineIndex+2));
            if(bignums){
                pointPrize = new Point(pointPrize.getX()+(long)1e13,pointPrize.getY()+(long)1e13);
            }

            machines.add(new ClawMachine(pointA, pointB, pointPrize));
        }
        return machines;
    }
    private static Point parsePoint(Pattern p, String line){
        Matcher m = p.matcher(line);
        m.find();
        return new Point(Long.parseLong(m.group(1)),Long.parseLong(m.group(2)));
    }
    public static String getPart01(List<String> input){
        ArrayList<ClawMachine> machines = generateClawMachineList(input, false);

        long total = 0;
        for(ClawMachine machine : machines){
            long tokens = machine.solve();
            if(tokens != -1){
                total += tokens;
            }
        }
        return Long.toString(total);
    }
    public static String getPart02(List<String> input){
        ArrayList<ClawMachine> machines = generateClawMachineList(input, true);

        long total = 0;
        for(ClawMachine machine : machines){
            long tokens = machine.solveBigNums();
            if(tokens != -1){
                total += tokens;
            }
        }
        return Long.toString(total);
    }

}