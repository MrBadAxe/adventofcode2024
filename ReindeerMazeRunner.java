public class ReindeerMazeRunner extends Point{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int direction;

    public ReindeerMazeRunner(long x, long y, int dir){
        super(x,y);
        this.direction = dir;
    }
    public ReindeerMazeRunner(long x, long y){
        this(x,y,ReindeerMazeRunner.EAST);
    }
    public int getDirection(){
        return this.direction;
    }
    public ReindeerMazeRunner moveForward(){
        switch(direction){
            case NORTH: return new ReindeerMazeRunner(this.getX()-1,this.getY(),direction);
            case EAST:  return new ReindeerMazeRunner(this.getX(),this.getY()+1,direction);
            case SOUTH: return new ReindeerMazeRunner(this.getX()+1,this.getY(),direction);
            case WEST:  return new ReindeerMazeRunner(this.getX(),this.getY()-1,direction);
        }
        return this;
    }
    public ReindeerMazeRunner turnLeft(){
        return new ReindeerMazeRunner(this.getX(),this.getY(),((direction-1)+4)%4);
    }
    public ReindeerMazeRunner turnRight(){
        return new ReindeerMazeRunner(this.getX(),this.getY(),(direction+1)%4);
    }
}