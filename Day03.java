import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day03{
    public static String getPart01(List<String> input){
        int total = 0;
        for(String str : input){
            Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher m = p.matcher(str);
            while(m.find()){
                int op1 = Integer.parseInt(m.group(1));
                int op2 = Integer.parseInt(m.group(2));
                total += (op1 * op2);
            }
        }
        return Integer.toString(total);
    }
}