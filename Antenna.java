public class Antenna extends Point{
    private final char frequency;

    public Antenna(long x, long y, char freq){
        super(x,y);
        this.frequency = freq;
    }

    public char getFrequency(){
        return this.frequency;
    }

    public Point findAntinode(Antenna other){
        long dx = other.getX() - this.getX();
        long dy = other.getY() - this.getY();
        return new Point(other.getX() + dx, other.getY() + dy);
    }

    public String toString(){
        return super.toString() + "=" + this.getFrequency();
    }
}