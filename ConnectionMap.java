import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectionMap{
    HashMap<String,ArrayList<String>> connections;

    public ConnectionMap(){
        connections = new HashMap<>();
    }
    public void addConnection(String nodeA, String nodeB){
        connections.putIfAbsent(nodeA, new ArrayList<String>());
        connections.get(nodeA).add(nodeB);
        connections.putIfAbsent(nodeB, new ArrayList<String>());
        connections.get(nodeB).add(nodeA);
    }
    public boolean isConnected(String nodeA, String nodeB){
        return connections.get(nodeA).contains(nodeB) || connections.get(nodeB).contains(nodeA);
    }
    public HashSet<String> findTriples(){
        HashSet<String> triples = new HashSet<>();

        for(String nodeA : connections.keySet()){
            ArrayList<String> connected = connections.get(nodeA);
            for(int k=0;k<connected.size()-1;k++){
                for(int j=k+1;j<connected.size();j++){
                    String nodeB = connected.get(k);
                    String nodeC = connected.get(j);
                    if(isConnected(nodeB, nodeC)){
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
    public boolean allConnected(ArrayList<String> nodes){
        boolean z = true;
        int size = nodes.size();
        for(int k=0;k<size-1;k++){
            for(int j=k+1;j<size;j++){
                String nodeA = nodes.get(k);
                String nodeB = nodes.get(j);
                z &= isConnected(nodeA, nodeB);
            }
        }
        return z;
    }
    public ArrayList<String> findMaximumClique(){
        ArrayList<String> maximumClique = new ArrayList<>();
        int maximumCliqueSize = 0;

        for(String start : connections.keySet()){
            ArrayList<String> potentialClique = new ArrayList<>();
            potentialClique.add(start);
            ArrayList<String> connectedNodes = connections.get(start);
            for(String node : connectedNodes){
                potentialClique.add(node);
                if(!this.allConnected(potentialClique)){
                    potentialClique.remove(node);
                }
            }
            if(potentialClique.size() > maximumCliqueSize){
                maximumCliqueSize = potentialClique.size();
                maximumClique = potentialClique;
            }
        }
        return maximumClique;
    }
}