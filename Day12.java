import java.util.List;
import java.util.ArrayList;

public class Day12{
    public static String getPart01(List<String> input){
        GardenMap map = new GardenMap(input);
        
        ArrayList<GardenMapRegion> regions = map.generateRegionsList();

        int total = 0;
        for(GardenMapRegion region : regions){
            //System.out.println(region);
            total += region.calculateFencingCost();
        }

        return Integer.toString(total);
    }
}