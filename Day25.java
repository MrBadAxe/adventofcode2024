import java.util.List;
import java.util.ArrayList;

public class Day25{
    private static ArrayList<Integer> parseHeightMap(List<String> input){
        int height = input.size();
        int width = input.get(0).length();
        boolean isLock = input.get(0).equals("#".repeat(width));
        ArrayList<Integer> heights = new ArrayList<>();
        for(int col=0;col<width;col++){
            heights.add(isLock ? 0 : 5);
        }
        for(int row=1;row<height;row++){
            String line = input.get(row);
            for(int col=0;col<width;col++){
                heights.set(col,heights.get(col) + (line.charAt(col) == '#' ? (isLock ? 1 : 0) : (isLock ? 0 : -1)));
            }
        }
        return heights;
    }
    private static boolean checkFit(ArrayList<Integer> lock, ArrayList<Integer> key){
        if(lock.size() != key.size()){ return false; };
        for(int col=0;col<lock.size();col++){
            if(lock.get(col) + key.get(col) > 5){
                return false;
            }
        }
        return true;
    }
    public static String getPart01(List<String> input){
        ArrayList<ArrayList<Integer>> keys = new ArrayList<>();
        ArrayList<ArrayList<Integer>> locks = new ArrayList<>();
        for(int k=0;k<input.size();k+=8){
            ArrayList<String> heightMap = new ArrayList<>();
            for(int row=0;row<6;row++){
                heightMap.add(input.get(k + row));
            }
            ArrayList<Integer> heightMapValues = parseHeightMap(heightMap);
            if(heightMap.get(0).equals("#".repeat(heightMap.get(0).length()))){
                locks.add(heightMapValues);
            }else{
                keys.add(heightMapValues);
            }
        }

        int total = 0;
        for(ArrayList<Integer> lock : locks){
            for(ArrayList<Integer> key : keys){
                if(checkFit(lock,key)){
                    total++;
                }
            }
        }
        return Integer.toString(total);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}