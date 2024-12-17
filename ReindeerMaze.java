import java.util.ArrayList;

public class ReindeerMaze extends CharGrid{
    private Point start;
    private Point end;

    public ReindeerMaze(int x, int y, char init){
        super(x,y,init);
    }
    public ReindeerMaze clone(){
        ReindeerMaze z = new ReindeerMaze(this.getHeight(),this.getWidth(),'.');
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                z.set(row,col,this.get(row,col));
            }
        }
        return z;
    }
    public Point getStart(){
        if(start != null){
            return start;
        }else{
            for(int row=0;row<this.getHeight();row++){
                for(int col=0;col<this.getWidth();col++){
                    if(this.get(row,col) == 'S'){
                        start = new Point(row,col);
                        return start;
                    }
                }
            }
            return new Point(-1,-1);
        }
    }
    public Point getEnd(){
        if(end != null){
            return end;
        }else{
            for(int row=0;row<this.getHeight();row++){
                for(int col=0;col<this.getWidth();col++){
                    if(this.get(row,col) == 'E'){
                        end = new Point(row,col);
                        return end;
                    }
                }
            }
            return new Point(-1,-1);
        }
    }
    public boolean equals(ReindeerMaze other){
        if(this.getHeight() != other.getHeight()){ return false; }
        if(this.getWidth() != other.getWidth()){ return false; }
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) != other.get(row,col)){ return false; }
            }
        }
        return true;
    }

    public ReindeerMaze pruneDeadEnds(){
        ReindeerMaze z = this.clone();
        for(int row=1;row<z.getHeight()-1;row++){
            for(int col=1;col<z.getWidth()-1;col++){
                if(z.get(row,col) == '.'){
                    int walls = 0;
                    for(Point p : new Point(row,col).getNeighbors()){
                        if(BoundsChecker.checkBounds(p,z) && z.get((int)p.getX(),(int)p.getY()) == '#')
                        walls++;
                    }
                    if(walls >= 3){
                        z.set(row,col,'#');
                    }    
                }
            }
        }
        return z;
    }

    public int[][] scorePaths(){
        int[][] pathScore = new int[this.getHeight()][this.getWidth()];
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                switch(this.get(row,col)){
                    case '#':
                        pathScore[row][col] = -999;
                        break;
                    case 'S':
                        pathScore[row][col] = 0;
                        break;        
                    case 'E':
                    case '.':
                        pathScore[row][col] = Integer.MAX_VALUE;
                        break;
                }
            }
        }

        ArrayList<ReindeerMazeRunner> runners = new ArrayList<>();
        runners.add(new ReindeerMazeRunner(this.getStart().getX(),this.getStart().getY()));
        while(runners.size() > 0){
            ReindeerMazeRunner runner = runners.removeFirst();
            int currentSpaceScore = pathScore[(int)runner.getX()][(int)runner.getY()];
            //System.out.println(runner + " " + currentSpaceScore);

            ReindeerMazeRunner forward = runner.moveForward();
            int fX = (int)forward.getX();
            int fY = (int)forward.getY();
            int forwardSpaceScore = pathScore[fX][fY];
            //System.out.print("forward: " + forward + " " + this.get(fX,fY));
            //System.out.print(" " + forwardSpaceScore + " " + (currentSpaceScore + 1));

            if(this.get(fX,fY) == '.' && currentSpaceScore + 1 < forwardSpaceScore){
                pathScore[fX][fY] = currentSpaceScore + 1;
                runners.add(forward);
            }else if(this.get(fX,fY) == 'E' && currentSpaceScore + 1 < forwardSpaceScore){
                pathScore[fX][fY] = currentSpaceScore + 1;
            }
            //System.out.println();
            //printPathScore(pathScore);

            ReindeerMazeRunner left = runner.turnLeft().moveForward();
            int lX = (int)left.getX();
            int lY = (int)left.getY();
            int leftSpaceScore = pathScore[lX][lY];
            //System.out.print("left: " + left + " " + this.get(lX,lY));
            //System.out.print(" " + leftSpaceScore + " " + (currentSpaceScore + 1001));

            if(this.get(lX,lY) == '.' && currentSpaceScore + 1001 < leftSpaceScore){
                pathScore[lX][lY] = currentSpaceScore + 1001;
                runners.add(left);
            }
            //System.out.println();
            //printPathScore(pathScore);

            
            ReindeerMazeRunner right = runner.turnRight().moveForward();
            int rX = (int)right.getX();
            int rY = (int)right.getY();
            int rightSpaceScore = pathScore[rX][rY];
            //System.out.print("right: " + right + " " + this.get(rX,rY));
            //System.out.print(" " + rightSpaceScore + " " + (currentSpaceScore + 1001));

            if(this.get(rX,rY) == '.' && currentSpaceScore + 1001 < rightSpaceScore){
                pathScore[rX][rY] = currentSpaceScore + 1001;
                runners.add(right);
            }
            //System.out.println();
            //printPathScore(pathScore);

            //System.out.println();
        }
        //printPathScore(pathScore);
        return pathScore;
    }
    private void printPathScore(int[][] pathScore){
        for(int row=0;row<pathScore.length;row++){
            for(int col=0;col<pathScore[0].length;col++){
                System.out.print(pathScore[row][col] + " ");
            }
            System.out.println();
        }
    }
}