import java.util.List;

public class Day02{
    private static boolean reportIsSafe(String[] report){
        int stepsUp = 0;
        int stepsDown = 0;
        int stepsBig = 0;
        for(int k=0;k<report.length-1;k++){
            if(Integer.parseInt(report[k+1]) > Integer.parseInt(report[k])){ stepsUp++; }
            if(Integer.parseInt(report[k+1]) < Integer.parseInt(report[k])){ stepsDown++; }
            if(Math.abs(Integer.parseInt(report[k+1]) - Integer.parseInt(report[k])) > 3){ stepsBig++; }
        }
        return ((stepsUp == report.length-1) || (stepsDown == report.length-1)) && stepsBig == 0;
    }

    public static String getPart01(List<String> input){
        int total = 0;
        for(String str : input){
            String[] split = str.split("\s+");
            if(reportIsSafe(split)){total++;}
        }
        return Integer.toString(total);
    }
}