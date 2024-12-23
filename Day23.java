import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.util.regex.*;

public class Day23{
    private static ConnectionMap generateConnectionMap(List<String> input){
        ConnectionMap connections = new ConnectionMap();
        for(String line : input){
            String[] split = line.split("-");
            String nodeA = split[0];
            String nodeB = split[1];

            connections.addConnection(nodeA, nodeB);
        }
        return connections;
    }
    public static String getPart01(List<String> input){
        ConnectionMap connections = generateConnectionMap(input);
        HashSet<String> triples = connections.findTriples();

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
        ConnectionMap connections = generateConnectionMap(input);
        ArrayList<String> maximumClique = connections.findMaximumClique();
        Collections.sort(maximumClique);
        return maximumClique.toString().replaceAll("[\s\\[\\]]","");
    }

}