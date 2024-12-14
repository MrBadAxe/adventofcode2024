import java.util.ArrayList;

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

    public long solveBigNums(){
        long buttonAPresses = (long)Math.ceil((double)prize.getX() / (double)buttonA.getX());
        long buttonBPresses = 0;

        long cycleLength = lcm(buttonA.getX(),buttonB.getX())/buttonA.getX() + lcm(buttonA.getX(),buttonB.getX())/buttonB.getX();

        ArrayList<Point> cycle = new ArrayList<>();

        long xdist = buttonAPresses * buttonA.getX() + buttonBPresses * buttonB.getX();
        long ydist = buttonAPresses * buttonA.getY() + buttonBPresses * buttonB.getY();

        long cycleButtonAPresses = Long.MIN_VALUE;
        long cycleButtonBPresses = Long.MIN_VALUE;

        for(int k=0;k<cycleLength*2 && cycle.size() < 2;k++){

            if(xdist >= prize.getX()){
                buttonAPresses--;
                if(cycleButtonAPresses != Long.MIN_VALUE){ cycleButtonAPresses--; }
            }else if(xdist < prize.getX()){
                buttonBPresses++;
                if(cycleButtonBPresses != Long.MIN_VALUE){ cycleButtonBPresses++; }
            }

            xdist = buttonAPresses * buttonA.getX() + buttonBPresses * buttonB.getX();
            ydist = buttonAPresses * buttonA.getY() + buttonBPresses * buttonB.getY();
            if(xdist == prize.getX()){
                if(cycleButtonAPresses == Long.MIN_VALUE){
                    cycleButtonAPresses = 0;
                    cycleButtonBPresses = 0;
                }
                cycle.add(new Point(xdist,ydist));    
            }
        }
        if(cycle.size() == 0){
            return -1; //cycle never touches x
        }
        long cycleYStep = cycle.getLast().getY() - cycle.getFirst().getY();
        if(cycle.getFirst().getY() % cycleYStep != prize.getY() % cycleYStep){
            return -1;  //cycle never touches y
        }

        long numCycleSteps = ((prize.getY() - ydist) / cycleYStep);

        buttonAPresses += cycleButtonAPresses*numCycleSteps;
        buttonBPresses += cycleButtonBPresses*numCycleSteps;
        xdist = buttonAPresses * buttonA.getX() + buttonBPresses * buttonB.getX();
        ydist = buttonAPresses * buttonA.getY() + buttonBPresses * buttonB.getY();

        return 3*buttonAPresses + buttonBPresses;
    }

    private long gcd(long a, long b){
        if(b > a){
            return gcd(b,a);
        }else if(b == 0){
            return a;
        }else{
            return gcd(b, a%b);
        }
    }
    private long lcm(long a, long b){
        return Math.abs(a*b) / gcd(a,b);
    }
}