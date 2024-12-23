import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

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
        
        ArrayList<SecretNumberMonkey> monkeys = new ArrayList<>();
        for(String line : input){
            SecretNumberMonkey monkey = new SecretNumberMonkey(Long.parseLong(line));
            monkeys.add(monkey);
        }
        long highestTotal = 0;
        HashSet<String> allSequences = new HashSet<>();
        for(SecretNumberMonkey monkey : monkeys){
            allSequences.addAll(monkey.getBananaCounts().keySet());
        }
        for(String sequence : allSequences){
            long totalBananas = 0;
            for(SecretNumberMonkey monkey : monkeys){
                totalBananas += monkey.getBananaCounts().getOrDefault(sequence,0L);
            }
            highestTotal = Math.max(highestTotal, totalBananas);
        }
        return Long.toString(highestTotal);
    }

}