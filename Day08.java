import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Day08{
    private static AntennaMap generateAntennaMap(List<String> input){
        AntennaMap map = new AntennaMap(input.size(), input.get(0).length(), '.');
        for(int row=0;row<map.getHeight();row++){
            String line = input.get(row);
            for(int col=0;col<map.getWidth();col++){
                map.set(row,col,line.charAt(col));
            }
        }
        return map;
    }
    public static String getPart01(List<String> input){
        AntennaMap map = generateAntennaMap(input);

        ArrayList<Antenna> antennas = map.findAllAntennas();
        HashSet<Point> antinodes = new HashSet<>();
        for(int k=0;k<antennas.size()-1;k++){
            for(int j=k+1;j<antennas.size();j++){
                Antenna a = antennas.get(k);
                Antenna b = antennas.get(j);
                if(a.getFrequency() == b.getFrequency()){
                    Point antiA = a.findFirstAntinode(b);
                    if(antiA.getX() >= 0 && antiA.getX() < map.getHeight() && antiA.getY() >= 0 && antiA.getY() < map.getWidth()){
                        antinodes.add(antiA);
                    }
                    Point antiB = b.findFirstAntinode(a);
                    if(antiB.getX() >= 0 && antiB.getX() < map.getHeight() && antiB.getY() >= 0 && antiB.getY() < map.getWidth()){
                        antinodes.add(antiB);
                    }
                }
            }
        }
        return Integer.toString(antinodes.size());
    }
    public static String getPart02(List<String> input){
        AntennaMap map = generateAntennaMap(input);

        ArrayList<Antenna> antennas = map.findAllAntennas();
        HashSet<Point> antinodes = new HashSet<>();
        for(int k=0;k<antennas.size()-1;k++){
            for(int j=k+1;j<antennas.size();j++){
                Antenna a = antennas.get(k);
                Antenna b = antennas.get(j);
                if(a.getFrequency() == b.getFrequency()){
                    antinodes.addAll(a.findAllAntinodes(b,map));
                    antinodes.addAll(b.findAllAntinodes(a,map));
                }
            }
        }
        System.out.println(antinodes);
        return Integer.toString(antinodes.size());
    }
}