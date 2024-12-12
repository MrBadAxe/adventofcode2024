import java.util.List;
import java.util.HashMap;

public class Day11{
    public static HashMap<Long,Long> blink(HashMap<Long,Long> stones){
        HashMap<Long,Long> z = new HashMap<>();
        for(long stone : stones.keySet()){
            if(stone == 0){
                if(z.get(1L) == null){
                    z.put(1L,0L);
                }
                z.put(1L,z.get(1L)+stones.get(stone));
            }else if(Long.toString(stone).length() % 2 == 0){
                String str = Long.toString(stone);
                long newStone1 = Long.parseLong(str.substring(0,str.length()/2));
                long newStone2 = Long.parseLong(str.substring(str.length()/2,str.length()));

                if(z.get(newStone1) == null){
                    z.put(newStone1,0L);
                }
                z.put(newStone1,z.get(newStone1)+stones.get(stone));

                if(z.get(newStone2) == null){
                    z.put(newStone2,0L);
                }
                z.put(newStone2,z.get(newStone2)+stones.get(stone));
            }else{
                long newStone = stone * 2024;
                if(z.get(newStone) == null){
                    z.put(newStone,0L);
                }
                z.put(newStone,z.get(newStone)+stones.get(stone));
            }
        }
        return z;
    }
    
    public static String getPart01(List<String> input){
        String startingStones = input.get(0);
        HashMap<Long,Long> stones = new HashMap<>();
        String[] split = startingStones.split("\s+");
        for(String str : split){
            long newStone = Long.parseLong(str);
            if(stones.get(newStone) == null){
                stones.put(newStone,0L);
            }
            stones.put(newStone,stones.get(newStone)+1L);
        }
        for(int k=0;k<25;k++){
            stones = blink(stones);
        }
        long total = 0;
        for(long stone : stones.keySet()){
            total += stones.get(stone);
        }
        return Long.toString(total);
    }
    public static String getPart02(List<String> input){
        String startingStones = input.get(0);
        HashMap<Long,Long> stones = new HashMap<>();
        String[] split = startingStones.split("\s+");
        for(String str : split){
            long newStone = Long.parseLong(str);
            if(stones.get(newStone) == null){
                stones.put(newStone,0L);
            }
            stones.put(newStone,stones.get(newStone)+1L);
        }
        for(int k=0;k<75;k++){
            stones = blink(stones);
        }
        long total = 0;
        for(long stone : stones.keySet()){
            total += stones.get(stone);
        }
        return Long.toString(total);
    }
}