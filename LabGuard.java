public class LabGuard extends Point{
    private int dir;
    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};
    

    public LabGuard(long startx, long starty){
        super(startx, starty);
        dir = 0;
    }
    private LabGuard(long startx, long starty, int dir){
        super(startx, starty);
        this.dir = dir;
    }

    public int getDir(){
        return this.dir;
    }
    public LabGuard nextMove(){
        return new LabGuard(this.getX()+dx[dir],this.getY()+dy[dir],this.getDir());
    }
    public void turnRight(){
        dir = (dir + 1)%4;
    }
}