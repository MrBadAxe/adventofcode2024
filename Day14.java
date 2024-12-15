import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class Day14{

    private static ArrayList<EBHQRobot> generateRobotList(List<String> input){
        Pattern p = Pattern.compile("p=(\\d+),(\\d+)\s+v=(-?\\d+),(-?\\d+)");
        ArrayList<EBHQRobot> z = new ArrayList<>();
        for(String line : input){
            Matcher m = p.matcher(line);
            m.find();
            long col = Long.parseLong(m.group(1));
            long row = Long.parseLong(m.group(2));
            long dCol = Long.parseLong(m.group(3));
            long dRow = Long.parseLong(m.group(4));
            EBHQRobot robot = new EBHQRobot(new Point(row,col), new Point(dRow,dCol));
            z.add(robot);
        }
        return z;
    }

    public static String getPart01(List<String> input){
        ArrayList<EBHQRobot> robots = generateRobotList(input);
        
        long height = 103;
        long width = 101;

        long q1 = 0;
        long q2 = 0;
        long q3 = 0;
        long q4 = 0;

        for(EBHQRobot robot : robots){
            for(int k=0;k<100;k++){
                robot.step(height, width);
            }
            if(robot.getPosition().getX() < (height-1)/2 && robot.getPosition().getY() < (width-1)/2){  q1++; }
            if(robot.getPosition().getX() > (height-1)/2 && robot.getPosition().getY() < (width-1)/2){  q2++; }
            if(robot.getPosition().getX() < (height-1)/2 && robot.getPosition().getY() > (width-1)/2){  q3++; }
            if(robot.getPosition().getX() > (height-1)/2 && robot.getPosition().getY() > (width-1)/2){  q4++; }
        }
        return Long.toString(q1*q2*q3*q4);
    }
    public static String viewPart02(List<String> input){
        ArrayList<EBHQRobot> robots = generateRobotList(input);
        
        long height = 103;
        long width = 101;

        long timer = 0;

        while(timer < 9999){
            CharGrid grid = new CharGrid((int)height,(int)width,'.');
            for(EBHQRobot robot : robots){
                grid.set((int)robot.getPosition().getX(),(int)robot.getPosition().getY(),'X');
            }
            System.out.println(grid);
            System.out.println("time = " + timer);

            for(EBHQRobot robot : robots){
                robot.step(height, width);
            }
            timer++;

            long delay = System.currentTimeMillis() + 1000;
            while(System.currentTimeMillis() < delay){}
        }
        return "";
    }
    public static String getPart02(List<String> input){
        ArrayList<EBHQRobot> robots = generateRobotList(input);
        
        long height = 103;
        long width = 101;

        int timer = 8;
        while(timer % 101 != 8 || timer % 103 != 78){
            timer += 103;
        }
        for(EBHQRobot robot : robots){
            for(int k=0;k<timer;k++){
                robot.step(height, width);
            }
        }
        CharGrid grid = new CharGrid((int)height,(int)width,'.');
        for(EBHQRobot robot : robots){
            grid.set((int)robot.getPosition().getX(),(int)robot.getPosition().getY(),'X');
        }
        System.out.println(grid);

        return Long.toString(timer);
    }

}