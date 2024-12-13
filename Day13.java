import java.util.List;
import java.util.regex.*;

public class Day13{
    private static Point parsePoint(Pattern p, String line){
        Matcher m = p.matcher(line);
        m.find();
        return new Point(Long.parseLong(m.group(1)),Long.parseLong(m.group(2)));
    }
    public static String getPart01(List<String> input){
        Pattern pBtnA = Pattern.compile("Button A: X\\+(\\d+),\s+Y\\+(\\d+)");
        Pattern pBtnB = Pattern.compile("Button B: X\\+(\\d+),\s+Y\\+(\\d+)");
        Pattern pPrize = Pattern.compile("Prize: X=(\\d+),\s+Y=(\\d+)");

        long total = 0;
        for(int machineIndex = 0; machineIndex < input.size(); machineIndex += 4){
            Point pointA = parsePoint(pBtnA, input.get(machineIndex));
            Point pointB = parsePoint(pBtnB, input.get(machineIndex+1));
            Point pointPrize = parsePoint(pPrize, input.get(machineIndex+2));

            ClawMachine machine = new ClawMachine(pointA, pointB, pointPrize);
            long tokens = machine.solve();
            if(tokens != -1){
                total += tokens;
            }
        }
        return Long.toString(total);
    }
}