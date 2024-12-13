import java.util.ArrayList;
import java.util.HashSet;

public class GardenMapRegion{
    char label;
    HashSet<Point> plots;

    public GardenMapRegion(char c){
        label = c;
        plots = new HashSet<Point>();
    }
    public void add(Point p){
        plots.add(p);
    }
    public char getLabel(){
        return this.label;
    }
    public String toString(){
        return "" + label + ": " + plots.toString();
    }
    public int calculatePerimeter(){
        int total = 0;
        for(Point plot : plots){
            ArrayList<Point> neighbors = plot.getNeighbors();
            for(Point neighbor : neighbors){
                if(!plots.contains(neighbor)){
                    //System.out.println(plot + "->" + neighbor);
                    total++;
                }
            }
        }
        return total;
    }
    public int calculateFencingCost(){
        return plots.size() * this.calculatePerimeter();
    }
}