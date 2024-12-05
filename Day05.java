import java.util.List;
import java.util.ArrayList;

public class Day05{
    private static ArrayList<PageOrderRule> generateRulesList(List<String> input){
        ArrayList<PageOrderRule> rules = new ArrayList<>();
        for(int lineIndex = 0; !input.get(lineIndex).equals(""); lineIndex++){
            String[] split = input.get(lineIndex).split("\\|");
            rules.add(new PageOrderRule(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        return rules;
    }
    private static ArrayList<Integer> parsePageList(String str){
        ArrayList<Integer> z = new ArrayList<>();
        String[] split = str.split(",");
        for(int k=0;k<split.length;k++){
            z.add(Integer.parseInt(split[k]));
        }
        return z;
    }
    private static boolean pagesInOrder(ArrayList<Integer> pages, ArrayList<PageOrderRule> rules){
        for(PageOrderRule rule : rules){
            int beforeIndex = pages.indexOf(rule.before());
            int afterIndex = pages.indexOf(rule.after());
            if(beforeIndex >= 0 && afterIndex >= 0 && afterIndex < beforeIndex){ return false; }
        }
        return true;
    }
    public static String getPart01(List<String> input){
        ArrayList<PageOrderRule> rules = generateRulesList(input);
        List<String> pageListLines = input.subList(rules.size()+1, input.size());

        int total = 0;
        for(String pageList : pageListLines){
            ArrayList<Integer> pages = parsePageList(pageList);
            if(pagesInOrder(pages, rules)){
                total += pages.get((pages.size() - 1) / 2);
            }
        }
        return Integer.toString(total);
    }
}