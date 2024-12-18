import java.util.ArrayList;
import java.util.HashMap;

public class MazeSolver{
    public static int findPathLength(CharGrid maze, Point start, Point end){
        return findPathLength(maze, start, end, '.', '#');
    }
    public static int findPathLength(CharGrid maze, Point start, Point end, char space, char wall){
        ArrayList<Point> unexplored = new ArrayList<>();
        for(int row=0;row<maze.getHeight();row++){
            for(int col=0;col<maze.getWidth();col++){
                if(maze.get(row,col) != wall){
                    unexplored.add(new Point(row,col));
                }
            }
        }
        
        HashMap<Point,Integer> explored = new HashMap<>();
        explored.put(start,0);
        unexplored.remove(start);
        //System.out.println(unexplored);
        //System.out.println(explored);
        while(unexplored.size() > 0){
            HashMap<Point,Integer> newlyExplored = new HashMap<>();
            for(Point p : explored.keySet()){
                for(Point neighbor : p.getNeighbors()){
                    if(unexplored.contains(neighbor)){
                        newlyExplored.put(neighbor,explored.get(p)+1);
                        unexplored.remove(neighbor);
                    }
                }
            }
            if(newlyExplored.size() > 0){
                for(Point candidate : newlyExplored.keySet()){
                    if(explored.get(candidate) == null){
                        explored.put(candidate,newlyExplored.get(candidate));
                    }else if(newlyExplored.get(candidate) < explored.get(candidate)){
                        explored.put(candidate,newlyExplored.get(candidate));
                    }
                }
            }else{
                unexplored.clear();
            }

            //System.out.println(unexplored);
            //System.out.println(explored);
        }        
        return (explored.get(end) == null) ? -1 : explored.get(end);
    }
}