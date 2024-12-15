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
            //System.out.print(robot);
            for(int k=0;k<100;k++){
                robot.step(height, width);
            }
            //System.out.println("\t\t" + robot);
            if(robot.getPosition().getX() < (height-1)/2 && robot.getPosition().getY() < (width-1)/2){  q1++; }
            if(robot.getPosition().getX() > (height-1)/2 && robot.getPosition().getY() < (width-1)/2){  q2++; }
            if(robot.getPosition().getX() < (height-1)/2 && robot.getPosition().getY() > (width-1)/2){  q3++; }
            if(robot.getPosition().getX() > (height-1)/2 && robot.getPosition().getY() > (width-1)/2){  q4++; }
        }
        return Long.toString(q1*q2*q3*q4);
    }
}