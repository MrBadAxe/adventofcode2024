import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Day19{

    private static ArrayList<String> generateTowelsList(String list){
        ArrayList<String> allOnsenTowels = new ArrayList<>();
        String[] split = list.split(",");
        for(String s : split){
            allOnsenTowels.add(s.strip());
        }
        return allOnsenTowels;
    }
    public static String getPart01(List<String> input){
        ArrayList<String> allOnsenTowels = generateTowelsList(input.get(0));

        ArrayList<String> designs = new ArrayList<>();
        for(int k=2;k<input.size();k++){
            designs.add(input.get(k));
        }

        HashSet<String> totalPossibleDesigns = new HashSet<>();
        LinkedHashSet<String> candidates = new LinkedHashSet<>();
        candidates.addAll(designs);
        
        while(candidates.size() > 0){
            String candidate = candidates.removeFirst();

            String[] split = candidate.split("\\|");
            String alreadyChecked = split.length > 1 ? split[0] : "";
            String remaining = split.length > 1 ? split[1] : split[0];

            ArrayList<String> newCandidates = new ArrayList<>();
            for(String towel : allOnsenTowels){
                if(remaining.length() >= towel.length()){
                    String startsWith = remaining.substring(0,towel.length());
                    String endsWith = remaining.substring(towel.length());
                    //System.out.println(startsWith + "|" + endsWith);
                    if(startsWith.equals(towel)){
                        if(endsWith.length() == 0){
                            totalPossibleDesigns.add(alreadyChecked + startsWith);
                        }else{
                            newCandidates.add(alreadyChecked + startsWith + "|" + endsWith);
                        }
                    }
                }
            }
            candidates.addAll(newCandidates);
        }

        return Integer.toString(totalPossibleDesigns.size());
    }
    public static String getPart02(List<String> input){
        return "";
    }

}