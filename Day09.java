import java.util.List;

public class Day09{
    public static String getPart01(List<String> input){
        String diskMap = input.get(0);
        AmphipodDisk disk = new AmphipodDisk(diskMap);
        disk.compressDisk();
        return Long.toString(disk.getChecksum());
    }
    public static String getPart02(List<String> input){
        String diskMap = input.get(0);
        AmphipodDisk disk = new AmphipodDisk(diskMap);
        disk.compressDiskUnfragmented(diskMap);
        return Long.toString(disk.getChecksum());
    }
}
