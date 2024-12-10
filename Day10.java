import java.util.List;
import java.util.ArrayList;

public class Day10{
    private static TopographicMap generateTopographicMap(List<String> input){
        TopographicMap map = new TopographicMap(input.size(), input.get(0).length(), '.');
        for(int row=0;row<map.getHeight();row++){
            String line = input.get(row);
            for(int col=0;col<map.getWidth();col++){
                map.set(row,col,line.charAt(col));
            }
        }
        return map;
    }
    public static String getPart01(List<String> input){
        TopographicMap map = generateTopographicMap(input);
        System.out.println(map);
        
        ArrayList<Point> trailheads = map.findAllTrailheads();
        System.out.println(trailheads);
        int total = 0;
        for(Point head : trailheads){
            total += map.scoreTrailhead(head);
        }
        return Integer.toString(total);
    }
}