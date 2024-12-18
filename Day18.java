import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18{
    private static ArrayList<Point> generateBytePositionsList(List<String> input){
        ArrayList<Point> z = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d+),(\\d+)");
        for(String line: input){
            Matcher m = p.matcher(line);
            m.find();
            z.add(new Point(Long.parseLong(m.group(1)),Long.parseLong(m.group(2))));
        }
        return z;
    }
    public static String getPart01(List<String> input){
        ArrayList<Point> bytePositions = generateBytePositionsList(input);
        //System.out.println(bytePositions);
        CharGrid memorySpace = new CharGrid(71,71,'.');
        for(int k=0;k<1024;k++){
            Point p = bytePositions.get(k);
            memorySpace.set((int)p.getX(),(int)p.getY(), '#');
        }
        //System.out.println(memorySpace);
        int pathLength = MazeSolver.findPathLength(memorySpace, new Point(0,0), new Point(70,70));
        return Integer.toString(pathLength);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}