import java.util.List;
import java.util.ArrayList;

public class Day15{
    private static WarehouseMap generateWarehouseMap(List<String> input){
        WarehouseMap z = new WarehouseMap(input.size(), input.get(0).length(), '.');
        for(int row=0;row<z.getHeight();row++){
            String line = input.get(row);
            for(int col=0;col<z.getWidth();col++){
                z.set(row,col,line.charAt(col));
            }
        }
        return z;
    }

    public static String getPart01(List<String> input){
        ArrayList<String> warehouseMapLines = new ArrayList<>();
        for(String line = input.removeFirst(); !line.equals(""); line = input.removeFirst()){
            warehouseMapLines.add(line);
        }
        WarehouseMap warehouseMap = generateWarehouseMap(warehouseMapLines);
        
        String orders = "";
        for(String line : input){
            orders += line;
        }

        for(int k=0;k<orders.length();k++){
            //System.out.println(warehouseMap);
            warehouseMap.moveRobot(orders.charAt(k));
        }
        return Long.toString(warehouseMap.calculateBoxGPSTotal());
    }
}