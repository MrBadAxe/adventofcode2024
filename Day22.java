import java.util.List;

public class Day22{
    private static long getNextSecretNumber(long n0){
        long n1 = ((n0 << 6) ^ n0) & 0xFFFFFF;
        long n2 = ((n1 >> 5) ^ n1) & 0xFFFFFF;
        long n3 = ((n2 << 11) ^ n2) & 0xFFFFFF;
        return n3;
    }
    public static String getPart01(List<String> input){
        long total = 0;
        for(String line : input){
            long n = Long.parseLong(line);
            for(int k=0;k<2000;k++){
                n = getNextSecretNumber(n);
            }    
            System.out.println(n);
            total += n;
        }
        return Long.toString(total);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}