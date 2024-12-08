import java.util.ArrayList;

public class AntennaMap extends CharGrid{
    public AntennaMap(int x, int y, char init){
        super(x,y,init);
    }

    public ArrayList<Antenna> findAllAntennas(){
        ArrayList<Antenna> z = new ArrayList<>();
        for(int row=0;row<this.getHeight();row++){
            for(int col=0;col<this.getWidth();col++){
                if(this.get(row,col) != '.'){
                    z.add(new Antenna(row,col,this.get(row,col)));
                }
            }
        }
        return z;
    }
}