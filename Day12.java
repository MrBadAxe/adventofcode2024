import java.util.List;
import java.util.ArrayList;

public class Day12{
    public static String getPart01(List<String> input){
        GardenMap map = new GardenMap(input);
        ArrayList<GardenMapRegion> regions = map.generateRegionsList();
        int total = 0;
        for(GardenMapRegion region : regions){
            total += region.calculateFencingCost();
        }
        return Integer.toString(total);
    }
    public static String getPart02(List<String> input){
        GardenMap map = new GardenMap(input);
        ArrayList<GardenMapRegion> regions = map.generateRegionsList();
        int total = 0;
        for(GardenMapRegion region : regions){
            total += region.calculateDiscountedFencingCost();
        }
        return Integer.toString(total);

    }
}