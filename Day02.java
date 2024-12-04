import java.util.List;

public class Day02{
    private static boolean reportIsSafe(String[] report){
        int stepsUp = 0;
        int stepsDown = 0;
        int stepsBig = 0;
        for(int k=0;k<report.length-1;k++){
            int before = Integer.parseInt(report[k]);
            int after = Integer.parseInt(report[k+1]);
            if(after > before){ stepsUp++; }
            if(after < before){ stepsDown++; }
            if(Math.abs(after - before) > 3){ stepsBig++; }
        }
        return ((stepsUp == report.length-1) || (stepsDown == report.length-1)) && stepsBig == 0;
    }

    private static boolean reportIsDampenedSafe(String[] report){
        boolean z = false;
        for(int k=0;k<report.length;k++){
            String[] dampened = new String[report.length-1];
            for(int j=0;j<report.length-1;j++){
                dampened[j] = j < k ? report[j] : report[j+1];
            }
            z |= reportIsSafe(dampened);
        }
        return z;
    }

    public static String getPart01(List<String> input){
        int total = 0;
        for(String str : input){
            String[] split = str.split("\s+");
            if(reportIsSafe(split)){total++;}
        }
        return Integer.toString(total);
    }
    public static String getPart02(List<String> input){
        int total = 0;
        for(String str : input){
            String[] split = str.split("\s+");
            if(reportIsDampenedSafe(split)){total++;}
        }
        return Integer.toString(total);
    }

}