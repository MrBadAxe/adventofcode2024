import java.util.ArrayList;

public class RacetrackMap extends CharGrid{

    private Point start;
    private Point end;

    public RacetrackMap(int x, int y, char init){
        super(x,y,init);
    }
    private Point getStart(){
        if(this.start == null){
            for(int row=0;row<this.getHeight();row++){
                for(int col=0;col<this.getWidth();col++){
                    if(this.get(row,col) == 'S'){
                        this.start = new Point(row,col);
                        row = this.getHeight();
                        col = this.getWidth();
                    }
                }
            }
        }
        return this.start;
    }
    private Point getEnd(){
        if(this.end == null){
            for(int row=0;row<this.getHeight();row++){
                for(int col=0;col<this.getWidth();col++){
                    if(this.get(row,col) == 'E'){
                        this.end = new Point(row,col);
                        row = this.getHeight();
                        col = this.getWidth();
                    }
                }
            }
        }
        return this.end;
    }

    public ArrayList<Point> toPath(){
        ArrayList<Point> path = new ArrayList<>();
        Point position = this.getStart();
        path.add(position);
        while(!position.equals(this.getEnd())){
            for(Point neighbor : position.getNeighbors()){
                if(!path.contains(neighbor) && this.get((int)neighbor.getX(),(int)neighbor.getY()) != '#'){
                    path.add(neighbor);
                }
            }
            position = path.getLast();
        }
        return path;
    }

}