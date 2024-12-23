import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.regex.*;

public class Day23{
    private static HashMap<String,ArrayList<String>> generateConnectionMap(List<String> input){
        HashMap<String,ArrayList<String>> connections = new HashMap<>();
        for(String line : input){
            String[] split = line.split("-");
            String a = split[0];
            String b = split[1];

            connections.putIfAbsent(a, new ArrayList<String>());
            connections.get(a).add(b);
            connections.putIfAbsent(b, new ArrayList<String>());
            connections.get(b).add(a);
        }
        return connections;
    }
    private static boolean isConnected(HashMap<String,ArrayList<String>> connections, String a, String b){
        return connections.get(a).contains(b) || connections.get(b).contains(a);
    }
    private static HashSet<String> findTriples(HashMap<String,ArrayList<String>> connections){
        HashSet<String> triples = new HashSet<>();

        for(String origin : connections.keySet()){
            ArrayList<String> connected = connections.get(origin);
            for(int k=0;k<connected.size()-1;k++){
                for(int j=k+1;j<connected.size();j++){
                    String a = connected.get(k);
                    String b = connected.get(j);
                    if(isConnected(connections, a, b)){
                        ArrayList<String> triple = new ArrayList<>();
                        triple.add(origin);
                        triple.add(a);
                        triple.add(b);
                        Collections.sort(triple);
                        triples.add(triple.toString());
                    }
                }
            }
        }
        return triples;
    }
    public static String getPart01(List<String> input){
        HashMap<String,ArrayList<String>> connections = generateConnectionMap(input);
        HashSet<String> triples = findTriples(connections);

        int containsT = 0;
        Pattern p = Pattern.compile("(\\w\\w), (\\w\\w), (\\w\\w)");
        for(String triple : triples){
            Matcher m = p.matcher(triple);
            m.find();
            if(m.group(1).charAt(0) == 't' || m.group(2).charAt(0) == 't' || m.group(3).charAt(0) == 't'){
                containsT++;
            }
        }
        return Integer.toString(containsT);
    }
    public static String getPart02(List<String> input){
        return "";
    }

}