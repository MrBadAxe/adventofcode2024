import java.util.List;
import java.util.ArrayList;

public class GardenMap extends CharGrid{
    public GardenMap(int x, int y, char init){
        super(x,y,init);
    }
    public GardenMap(List<String> input){
        this(input.size(),input.get(0).length(),'.');
        for(int row=0;row<this.getHeight();row++){
            String line = input.get(row);
            for(int col=0;col<this.getWidth();col++){
                this.set(row,col,line.charAt(col));
            }
        }
    }

    public ArrayList<GardenMapRegion> generateRegionsList(){
        ArrayList<GardenMapRegion> z = new ArrayList<GardenMapRegion>();
        boolean[][] isMapped = new boolean[this.getHeight()][this.getWidth()];
        for(int row=0;row<isMapped.length;row++){
            for(int col=0;col<isMapped[row].length;col++){
                isMapped[row][col] = false;
            }
        }
        for(int row=0;row<isMapped.length;row++){
            for(int col=0;col<isMapped[row].length;col++){
                if(!isMapped[row][col]){
                    GardenMapRegion r = new GardenMapRegion(this.get(row,col));
                    ArrayList<Point> floodFill = new ArrayList<>();
                    floodFill.add(new Point(row,col));
                    while(floodFill.size() > 0){
                        Point p = floodFill.removeFirst();
                        int x = (int)p.getX();
                        int y = (int)p.getY();
                        if(!isMapped[x][y] && this.get(x,y) == r.getLabel()){
                            r.add(p);
                            isMapped[x][y] = true;
                            ArrayList<Point> neighbors = p.getNeighbors();
                            for(Point neighbor : neighbors){
                                if(BoundsChecker.checkBounds(neighbor, this)){
                                    floodFill.add(neighbor);
                                }    
                            }
                        }
                    }
                    z.add(r);
                }
            }
        }
        return z;
    }
}