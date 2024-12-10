import java.util.ArrayList;
import java.util.HashSet;

public class TopographicMap extends CharGrid{
    public TopographicMap(int x, int y, char init){
        super(x,y,init);
    }
    public ArrayList<Point> findAllTrailheads(){
        ArrayList<Point> z = new ArrayList<>();
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) == '0'){
                    z.add(new Point(row,col));
                }
            }
        }
        return z;
    }
    public int scoreTrailhead(Point head){
        ArrayList<Point> reachable = new ArrayList<>();
        HashSet<Point> peaks = new HashSet<>();
        reachable.addAll(getReachable(head));
        while(reachable.size() > 0){
            Point next = reachable.removeFirst();
            if(this.get((int)next.getX(),(int)next.getY()) == '9'){
                peaks.add(next);
            }else{
                reachable.addAll(getReachable(next));
            }
        }
        System.out.println(peaks);
        return peaks.size();
    }

    public ArrayList<Point> getReachable(Point p){
        ArrayList<Point> z = new ArrayList<>();
        int row = (int)p.getX();
        int col = (int)p.getY();
        char height = this.get(row,col);
        if(row > 0                  && this.get(row-1,col) == height+1){ z.add(new Point(row-1,col)); }
        if(row < this.getHeight()-1 && this.get(row+1,col) == height+1){ z.add(new Point(row+1,col)); }
        if(col > 0                  && this.get(row,col-1) == height+1){ z.add(new Point(row,col-1)); }
        if(col < this.getWidth()-1  && this.get(row,col+1) == height+1){ z.add(new Point(row,col+1)); }
        return z;
    }
}