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
        return this.generateMap().toString();
    }
    public int calculatePerimeter(){
        int total = 0;
        for(Point plot : plots){
            ArrayList<Point> neighbors = plot.getNeighbors();
            for(Point neighbor : neighbors){
                if(!plots.contains(neighbor)){
                    total++;
                }
            }
        }
        return total;
    }
    public int calculateFencingCost(){
        return plots.size() * this.calculatePerimeter();
    }
    public int calculateSidesCount(){
        CharGrid regionMap = this.generateMap();

        int horizontalSides = 0;
        for(int row=0;row<regionMap.getHeight()-1;row++){
            int otherRow = row + 1;
            StringBuilder sideBuilder = new StringBuilder("");
            for(int col=0;col<regionMap.getWidth();col++){
                if(regionMap.get(row,col) == this.getLabel() && regionMap.get(otherRow,col) != this.getLabel()){
                    sideBuilder.append('v');
                }else if(regionMap.get(row,col) != this.getLabel() && regionMap.get(otherRow,col) == this.getLabel()){
                    sideBuilder.append('^');
                }else{
                    sideBuilder.append(' ');
                }
            }
            String sides = sideBuilder.toString();
            sides = sides.replaceAll("v\\^","v \\^").replaceAll("\\^v","\\^ v");
            if(!sides.replace("\s","").isEmpty()){
                String[] split = sides.toString().strip().split("\s+");
                horizontalSides += split.length;    
            }
        }
        int verticalSides = 0;
        for(int col=0;col<regionMap.getWidth()-1;col++){
            int otherCol = col + 1;
            StringBuilder sideBuilder = new StringBuilder("");
            for(int row=0;row<regionMap.getHeight();row++){
                if(regionMap.get(row,col) == this.getLabel() && regionMap.get(row,otherCol) != this.getLabel()){
                    sideBuilder.append('>');
                }else if(regionMap.get(row,col) != this.getLabel() && regionMap.get(row,otherCol) == this.getLabel()){
                    sideBuilder.append('<');
                }else{
                    sideBuilder.append(' ');
                }
            }
            String sides = sideBuilder.toString();
            sides = sides.replaceAll("><","> <").replaceAll("<>","< >");
            if(!sides.replace("\s","").isEmpty()){
                String[] split = sides.toString().strip().split("\s+");
                verticalSides += split.length;    
            }
        }
        return horizontalSides + verticalSides;
    }
    public int calculateDiscountedFencingCost(){
        return plots.size() * this.calculateSidesCount();
    }
    private CharGrid generateMap(){
        int xmin = Integer.MAX_VALUE;
        int ymin = Integer.MAX_VALUE;
        int xmax = 0;
        int ymax = 0;
        for(Point plot : plots){
            xmin = Math.min(xmin, (int)plot.getX());
            ymin = Math.min(ymin, (int)plot.getY());
            xmax = Math.max(xmax, (int)plot.getX());
            ymax = Math.max(ymax, (int)plot.getY());
        }
        int height = (xmax - xmin) + 1;
        int width = (ymax - ymin) + 1;
        CharGrid z = new CharGrid(height+2,width+2,'.');
        for(Point plot : plots){
            int x = (int)plot.getX();
            int y = (int)plot.getY();
            z.set((x+1)-xmin,(y+1)-ymin,this.getLabel());
        }
        return z;
    }
}