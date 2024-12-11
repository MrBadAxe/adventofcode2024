import java.util.List;
import java.util.ArrayList;

public class Day11{
    public static ArrayList<Long> blink(ArrayList<Long> stones){
        ArrayList<Long> z = new ArrayList<>();
        for(long stone : stones){
            if(stone == 0){
                z.add(1L);
            }else if(Long.toString(stone).length() % 2 == 0){
                String str = Long.toString(stone);
                String newStone1 = str.substring(0,str.length()/2);
                String newStone2 = str.substring(str.length()/2,str.length());
                z.add(Long.parseLong(newStone1));
                z.add(Long.parseLong(newStone2));
            }else{
                z.add(stone * 2024);
            }
        }
        return z;
    }
    
    public static String getPart01(List<String> input){
        String startingStones = input.get(0);
        ArrayList<Long> stones = new ArrayList<>();
        String[] split = startingStones.split("\s+");
        for(String str : split){
            stones.add(Long.parseLong(str));
        }
        System.out.println(stones);
        for(int k=0;k<25;k++){
            stones = blink(stones);
            System.out.println(stones);
        }
        return Integer.toString(stones.size());
    }
}