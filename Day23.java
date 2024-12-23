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
            String nodeA = split[0];
            String nodeB = split[1];

            connections.putIfAbsent(nodeA, new ArrayList<String>());
            connections.get(nodeA).add(nodeB);
            connections.putIfAbsent(nodeB, new ArrayList<String>());
            connections.get(nodeB).add(nodeA);
        }
        return connections;
    }
    private static boolean isConnected(HashMap<String,ArrayList<String>> connections, String nodeA, String nodeB){
        return connections.get(nodeA).contains(nodeB) || connections.get(nodeB).contains(nodeA);
    }
    private static HashSet<String> findTriples(HashMap<String,ArrayList<String>> connections){
        HashSet<String> triples = new HashSet<>();

        for(String nodeA : connections.keySet()){
            ArrayList<String> connected = connections.get(nodeA);
            for(int k=0;k<connected.size()-1;k++){
                for(int j=k+1;j<connected.size();j++){
                    String nodeB = connected.get(k);
                    String nodeC = connected.get(j);
                    if(isConnected(connections, nodeB, nodeC)){
                        ArrayList<String> triple = new ArrayList<>();
                        triple.add(nodeA);
                        triple.add(nodeB);
                        triple.add(nodeC);
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