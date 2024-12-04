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

    public static String getPart02(List<String> input){
        int total = 0;
        String str = "";
        for(String s : input){
            str += s;
        }
        str = "do()" + str + "don't()";
        Pattern p1 = Pattern.compile("do\\(\\)(.*?)don't\\(\\)");
        Matcher m1 = p1.matcher(str);
        while(m1.find()){
            Pattern p2 = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher m2 = p2.matcher(m1.group());
            while(m2.find()){
                int op1 = Integer.parseInt(m2.group(1));
                int op2 = Integer.parseInt(m2.group(2));
                total += (op1 * op2);
            }
        }
        return Integer.toString(total);
    }    
}