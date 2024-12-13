import java.util.ArrayList;

public class Point{
  private final long x;
  private final long y;

  public Point(long x, long y){
    this.x = x;
    this.y = y;
  }

  public Point(){
    this(0,0);
  }

  public long getX(){
    return this.x;
  }

  public long getY(){
    return this.y;
  }

  public ArrayList<Point> getNeighbors(){
    return this.getNeighbors(false);
  }
  public ArrayList<Point> getNeighbors(boolean includeDiagonals){
    ArrayList<Point> z = new ArrayList<>();
    z.add(new Point(this.getX()-1, this.getY()));
    z.add(new Point(this.getX()+1, this.getY()));
    z.add(new Point(this.getX(), this.getY()-1));
    z.add(new Point(this.getX(), this.getY()+1));
    if(includeDiagonals){
      z.add(new Point(this.getX()-1, this.getY()-1));
      z.add(new Point(this.getX()+1, this.getY()-1));
      z.add(new Point(this.getX()-1, this.getY()+1));
      z.add(new Point(this.getX()+1, this.getY()+1));  
    }
    return z;
  }

  public long taxicabDistance(Point other){
    return Math.abs(other.getX() - this.getX()) + Math.abs(other.getY() - this.getY());
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof Point)){ return false; }
    Point other = (Point)o;
    return (this.getX() == other.getX() && this.getY() == other.getY());
  }

  public String toString(){
    return "(" + this.getX() + "," + this.getY() + ")";
  }

  @Override
  public int hashCode(){
    return Long.hashCode(this.getX()) + Long.hashCode(this.getY());
  }
}
