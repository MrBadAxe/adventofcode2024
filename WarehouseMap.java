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
        StringBuilder pathBuilder = new StringBuilder("");

        switch(direction){
            case '^':
                for(int row=(int)robot.getX();!pathBuilder.toString().contains("#");row--){
                    pathBuilder.append(this.get(row,(int)robot.getY()));
                }
                break;
            case '<':
                for(int col=(int)robot.getY();!pathBuilder.toString().contains("#");col--){
                    pathBuilder.append(this.get((int)robot.getX(),col));
                }
                break;
            case '>':
                for(int col=(int)robot.getY();!pathBuilder.toString().contains("#");col++){
                    pathBuilder.append(this.get((int)robot.getX(),col));
                }
                break;
            case 'v':
                for(int row=(int)robot.getX();!pathBuilder.toString().contains("#");row++){
                    pathBuilder.append(this.get(row,(int)robot.getY()));
                }
                break;
        }
        String path = pathBuilder.toString();
        path = pushBoxes(path);

        for(int k=0;k<path.length();k++){
            switch(direction){
                case '^':
                    this.set((int)robot.getX()-k,(int)robot.getY(),path.charAt(k));
                    break;
                case '<':
                    this.set((int)robot.getX(),(int)robot.getY()-k,path.charAt(k));
                    break;
                case '>':
                    this.set((int)robot.getX(),(int)robot.getY()+k,path.charAt(k));
                    break;
                case 'v':
                    this.set((int)robot.getX()+k,(int)robot.getY(),path.charAt(k));
                    break;
            }    
        }
        locateRobot();
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