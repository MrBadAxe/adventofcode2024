import java.util.ArrayList;

public class LabMap extends CharGrid{
    private LabGuard guard;

    public LabMap(int x, int y, char init){
        super(x,y,init);
    }
    public LabMap clone(){
        LabMap z = new LabMap(this.getHeight(),this.getWidth(),'.');
        for(int row=0;row<HEIGHT;row++){
          for(int col=0;col<WIDTH;col++){
            z.set(row,col,this.get(row,col));
          }
        }
        return z;
    }
    public LabGuard findGuard(){
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row, col) == '^'){
                    return new LabGuard(row, col);
                }
            }
        }
        return null;
    }
    public int trackGuard(){
        guard = findGuard();
        this.set((int)guard.getX(),(int)guard.getY(),'G');
        int x = (int)guard.nextMove().getX();
        int y = (int)guard.nextMove().getY();
        while(x >= 0 && x < this.getHeight() && y >= 0 && y < this.getWidth()){
            if(this.get(x,y) == '#'){
                guard.turnRight();
            }else{
                this.set(x,y,'G');
                guard = guard.nextMove();
            }
            x = (int)guard.nextMove().getX();
            y = (int)guard.nextMove().getY();
        }
        int total = 0;
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) == 'G'){
                    total++;
                }
            }
        }
        return total;
    }
    public boolean endsInLoop(){
        guard = findGuard();
        ArrayList<Point> guardTurns = new ArrayList<Point>();

        int x = (int)guard.nextMove().getX();
        int y = (int)guard.nextMove().getY();
        while(x >= 0 && x < this.getHeight() && y >= 0 && y < this.getWidth()){
            if(this.get(x,y) == '#'){
                if(guardTurns.contains(guard) && guardTurns.indexOf(guard) != guardTurns.size()-1){
                    return true;
                }else{
                    guardTurns.add(guard);
                    guard.turnRight();
                }
            }else{
                guard = guard.nextMove();
            }
            x = (int)guard.nextMove().getX();
            y = (int)guard.nextMove().getY();
        }
        return false;
    }
}