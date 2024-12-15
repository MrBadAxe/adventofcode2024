public class EBHQRobot{
    private Point position;
    private Point velocity;

    public EBHQRobot(Point pos, Point vel){
        this.position = pos;
        this.velocity = vel;
    }
    public EBHQRobot(long row, long col, long dRow, long dCol){
        this.position = new Point(row,col);
        this.velocity = new Point(dRow,dCol);
    }

    public Point getPosition(){
        return this.position;
    }
    public Point getVelocity(){
        return this.velocity;
    }
    public String toString(){
        return this.position.toString() + "->" + this.velocity.toString();
    }
    public void step(long height, long width){
        long row = this.getPosition().getX();
        long col = this.getPosition().getY();

        long dRow = this.getVelocity().getX();
        long dCol = this.getVelocity().getY();

        long newRow = (row + dRow + height) % height;
        long newCol = (col + dCol + width) % width;
        position = new Point(newRow,newCol);
    }
}