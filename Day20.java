import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Day20{
    private static RacetrackMap generateRacetrackMap(List<String> input){
        RacetrackMap map = new RacetrackMap(input.size(), input.get(0).length(), '.');
        for(int row=0;row<map.getHeight();row++){
            String line = input.get(row);
            for(int col=0;col<map.getWidth();col++){
                map.set(row,col,line.charAt(col));
            }
        }
        return map;
    }
    private static ArrayList<Point> cheatLandings(Point origin){
        ArrayList<Point> z = new ArrayList<Point>();
        long x = origin.getX();
        long y = origin.getY();

        z.add(new Point(x-2,y));
        z.add(new Point(x-1,y-1));
        z.add(new Point(x,y-2));
        z.add(new Point(x+1,y-1));
        z.add(new Point(x+2,y));
        z.add(new Point(x+1,y+1));
        z.add(new Point(x,y+2));
        z.add(new Point(x-1,y+1));

        return z;
    }
    public static String getPart01(List<String> input){
        RacetrackMap map = generateRacetrackMap(input);
        ArrayList<Point> path = map.toPath();
        HashMap<Integer,Integer> numCheats = new HashMap<>();

        for(Point p : path){
            for(Point cheat : cheatLandings(p)){
                if(path.contains(cheat)){
                    int from = path.indexOf(p);
                    int to = path.indexOf(cheat);
                    if(to > from+2){
                        int saved = ((to-from)-2);
                        System.out.println("can jump from " + p + " to " + cheat + " saving " + saved);
                        if(numCheats.get(saved) == null){
                            numCheats.put(saved,0);
                        }
                        numCheats.put(saved,numCheats.get(saved)+1);
                    }
                }
            }
        }

        int total = 0;
        for(int saved : numCheats.keySet()){
            if(saved >= 100){
                total += numCheats.get(saved);
            }
        }
        return Integer.toString(total);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}