import java.util.List;

public class Day04{
    private static WordSearch generateWordSearch(List<String> input){
        WordSearch grid = new WordSearch(input.size(), input.get(0).length(), '.');
        for(int row = 0; row < input.size(); row++){
            String str = input.get(row);
            for(int col = 0; col < str.length(); col++){
                grid.set(row,col,str.charAt(col));
            }
        }
        return grid;
    }
    public static String getPart01(List<String> input){
        WordSearch grid = generateWordSearch(input);
        return Integer.toString(grid.getTotalXmasLineCount());
    }
    public static String getPart02(List<String> input){
        WordSearch grid = generateWordSearch(input);
        return Integer.toString(grid.getTotalXmasXCount());
    }
}