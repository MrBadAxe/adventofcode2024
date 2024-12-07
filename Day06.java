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
		int total = map.trackGuard();
		return Integer.toString(total);
    }
	public static String getPart02(List<String> input){
        LabMap map = new LabMap(input.size(),input.get(0).length(),'.');
        for(int row=0;row<input.size();row++){
            String str = input.get(row);
			for(int col=0;col<str.length();col++){
				map.set(row,col,str.charAt(col));
			}
        }
		int total = 0;
		for(int row=0;row<map.getHeight();row++){
			for(int col=0;col<map.getWidth();col++){
				if(map.get(row,col) != '^'){
					LabMap obstacle = map.clone();
					obstacle.set(row,col,'#');
					if(obstacle.endsInLoop()){
						total++;
					}	
				}
			}
		}
		return Integer.toString(total);
    }
}