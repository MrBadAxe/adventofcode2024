import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18{
    private static int MEMORY_SPACE_DIM = 71;
    private static int FIRST_PASS_CORRUPT = 1024;

    private static void useShort(){
        MEMORY_SPACE_DIM = 7;
        FIRST_PASS_CORRUPT = 12;
    }

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
    private static CharGrid corrupt(CharGrid grid, ArrayList<Point> bytePositions, int count){
        CharGrid z = grid.clone();
        for(int k=0;k<count;k++){
            Point p = bytePositions.get(k);
            z.set((int)p.getX(),(int)p.getY(),'#');
        }
        return z;
    }
    public static String getPart01(List<String> input){
        ArrayList<Point> bytePositions = generateBytePositionsList(input);
        CharGrid memorySpace = new CharGrid(MEMORY_SPACE_DIM,MEMORY_SPACE_DIM,'.');
        memorySpace = corrupt(memorySpace,bytePositions,FIRST_PASS_CORRUPT);
        int pathLength = MazeSolver.findPathLength(memorySpace, new Point(0,0), new Point(MEMORY_SPACE_DIM-1,MEMORY_SPACE_DIM-1));
        return Integer.toString(pathLength);
    }
    public static String getPart02(List<String> input){
        ArrayList<Point> bytePositions = generateBytePositionsList(input);
        CharGrid memorySpace = new CharGrid(MEMORY_SPACE_DIM,MEMORY_SPACE_DIM,'.');

        int lb = FIRST_PASS_CORRUPT;
        int ub = input.size();
        while(lb != ub){
            int midpoint = (lb+ub)/2;
            memorySpace = corrupt(new CharGrid(MEMORY_SPACE_DIM,MEMORY_SPACE_DIM,'.'),bytePositions,midpoint);
            int pathLength = MazeSolver.findPathLength(memorySpace, new Point(0,0), new Point(MEMORY_SPACE_DIM-1,MEMORY_SPACE_DIM-1));
            if(pathLength == -1){
                ub = midpoint;
            }else{
                lb = midpoint+1;
            }
        }
        return input.get(lb-1).toString();
    }

}