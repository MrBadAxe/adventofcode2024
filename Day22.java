import java.util.List;

public class Day22{
    public static String getPart01(List<String> input){
        long total = 0;
        for(String line : input){
            SecretNumberMonkey monkey = new SecretNumberMonkey(Long.parseLong(line));    
            total += monkey.getSecretNumber(2000);
        }
        return Long.toString(total);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}