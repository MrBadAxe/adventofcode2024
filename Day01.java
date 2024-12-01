import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Day01{
    private static ArrayList<ArrayList<Integer>> generateLeftRightLists(List<String> input){
        ArrayList<ArrayList<Integer>> z = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for(String str : input){
            String[] split = str.split("\s+");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
        z.add(left);
        z.add(right);
        return z;
    }

    public static String getPart01(List<String> input){
        ArrayList<ArrayList<Integer>> splitLists = generateLeftRightLists(input);
        ArrayList<Integer> left = splitLists.get(0);
        ArrayList<Integer> right = splitLists.get(1);

        int total = 0;
        for(int k=0;k<left.size();k++){
            total += Math.abs(left.get(k) - right.get(k));
        }
        return Integer.toString(total);
    }
}