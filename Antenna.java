import java.util.List;
import java.util.ArrayList;

public class Antenna extends Point{
    private final char frequency;

    public Antenna(long x, long y, char freq){
        super(x,y);
        this.frequency = freq;
    }

    public char getFrequency(){
        return this.frequency;
    }

    public Point findFirstAntinode(Antenna other){
        long dx = other.getX() - this.getX();
        long dy = other.getY() - this.getY();
        return new Point(other.getX() + dx, other.getY() + dy);
    }
    public List<Point> findAllAntinodes(Antenna other, CharGrid map){
        long dx = other.getX() - this.getX();
        long dy = other.getY() - this.getY();
        long n = 0;
        ArrayList<Point> z = new ArrayList<>();
        Point p = new Point(other.getX() + (dx * n), other.getY() + (dy * n));
        while(p.getX() >= 0 && p.getX() < map.getHeight() && p.getY() >= 0 && p.getY() < map.getWidth()){
            z.add(p);
            n++;
            p = new Point(other.getX() + (dx * n), other.getY() + (dy * n));
        }
        return z;
    }

    public String toString(){
        return super.toString() + "=" + this.getFrequency();
    }
}