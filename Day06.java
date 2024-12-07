import java.util.List;

public class Day06{
    public static String getPart01(List<String> input){
        LabMap map = new LabMap(input.size(),input.get(0).length(),'.');
        for(int row=0;row<input.size();row++){
            String str = input.get(row);
			for(int col=0;col<str.length();col++){
				map.set(row,col,str.charAt(col));
			}
        }
		System.out.println(map);
		int total = map.trackGuard();
		return Integer.toString(total);
    }
}