public class LabMap extends CharGrid{
    private LabGuard guard;
    public LabMap(int x, int y, char init){
        super(x,y,init);
    }
    public void findGuard(){
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row, col) == '^'){
                    guard = new LabGuard(row, col);
                    this.set(row,col,'G');
                }
            }
        }
    }
    public int trackGuard(){
        int x = (int)guard.nextMove().getX();
        int y = (int)guard.nextMove().getY();
        while(x >= 0 && x < this.getHeight() && y >= 0 && y < this.getWidth()){
            //System.out.println(guard.toString());
            //System.out.println(guard.nextMove().toString());
            if(this.get(x,y) == '#'){
                guard.turnRight();
            }else{
                this.set(x,y,'G');
                guard = guard.nextMove();
            }
            x = (int)guard.nextMove().getX();
            y = (int)guard.nextMove().getY();
            //System.out.println(this);
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
}