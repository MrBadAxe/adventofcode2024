public class WarehouseMap extends CharGrid{
    private Point robot;

    public WarehouseMap(int x, int y, char init){
        super(x,y,init);
        this.locateRobot();
    }
    private void locateRobot(){
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) == '@'){
                    this.robot = new Point(row,col);
                    row=this.getHeight();
                    col=this.getWidth();
                }
            }
        }
    }
    private String pushBoxes(String path){
        if(!path.contains(".")){ return path; }
        String moveable = path.substring(0,path.indexOf(".")+1);
        String notMoved = path.substring(path.indexOf(".")+1);
        
        String pushed = '.' + moveable.substring(0,moveable.length()-1);
        return pushed + notMoved;
    }
    public void moveRobot(char direction){
        locateRobot();
        push2(robot,direction);
        locateRobot();
    }
    public boolean push2(Point origin, char direction){
        //System.out.println(origin + " " + direction);
        Point destination = origin;
        switch(direction){
            case '^': destination = new Point(origin.getX()-1,origin.getY()); break;
            case 'v': destination = new Point(origin.getX()+1,origin.getY()); break;
            case '<': destination = new Point(origin.getX(),origin.getY()-1); break;
            case '>': destination = new Point(origin.getX(),origin.getY()+1); break;
        }
        if(destination == origin){ return false; }
        Point otherHalf;
        switch(this.get((int)destination.getX(),(int)destination.getY())){
            case 'O':
                if(this.clone().push2(destination,direction)){
                    this.push2(destination,direction);
                }else{
                    return false;
                }
                break;
            case '[':
                if(direction == '^' || direction == 'v'){
                    otherHalf = new Point(destination.getX(),destination.getY()+1);
                    if(this.clone().push2(destination,direction) && this.clone().push2(otherHalf,direction)){
                        this.push2(destination,direction);
                        this.push2(otherHalf,direction);
                    }else{
                        return false;
                    }    
                }else{
                    if(this.clone().push2(destination,direction)){
                        this.push2(destination,direction);
                    }else{
                        return false;
                    }
                }
                break;
            case ']':
                if(direction == '^' || direction == 'v'){
                    otherHalf = new Point(destination.getX(),destination.getY()-1);
                    if(this.clone().push2(destination,direction) && this.clone().push2(otherHalf,direction)){
                        this.push2(destination,direction);
                        this.push2(otherHalf,direction);
                    }else{
                        return false;
                    }
                }else{
                    if(this.clone().push2(destination,direction)){
                        this.push2(destination,direction);
                    }else{
                        return false;
                    }
                }
                break;
            case '#':
                return false;
        }
        char pushedBlock = this.get((int)origin.getX(),(int)origin.getY());
        this.set((int)origin.getX(),(int)origin.getY(),'.');
        this.set((int)destination.getX(),(int)destination.getY(),pushedBlock);

        return true;
    }

    public long calculateBoxGPSTotal(){
        long total = 0;
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) == 'O'){
                    total += (100*row) + col;
                }
            }
        }
        return total;
    }
}