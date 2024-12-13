public class ClawMachine{
    private Point buttonA;
    private Point buttonB;
    private Point prize;

    public ClawMachine(Point a, Point b, Point prize){
        this.buttonA = a;
        this.buttonB = b;
        this.prize = prize;
    }

    public long solve(){
        long buttonAPresses = (long)Math.ceil((double)prize.getX() / (double)buttonA.getX());
        long buttonBPresses = 0;
        long xdist = buttonAPresses * buttonA.getX() + buttonBPresses * buttonB.getX();
        long ydist = buttonAPresses * buttonA.getY() + buttonBPresses * buttonB.getY();
        while(buttonAPresses > 0 && buttonBPresses <= 100){
            if(xdist >= prize.getX()){
                buttonAPresses--;
            }else if(xdist < prize.getX()){
                buttonBPresses++;
            }
            xdist = buttonAPresses * buttonA.getX() + buttonBPresses * buttonB.getX();
            ydist = buttonAPresses * buttonA.getY() + buttonBPresses * buttonB.getY();
            if(xdist == prize.getX() && ydist == prize.getY()){
                return 3*buttonAPresses + buttonBPresses;
            }
        }
        return -1;
    }
}