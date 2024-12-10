import java.util.List;

public class Day09{
    public static String getPart01(List<String> input){
        String diskMap = input.get(0);
        //int[] disk = generateDiskFromMap(diskMap);
        AmphipodDisk disk = new AmphipodDisk(diskMap);
        disk.compressDisk();
        return Long.toString(disk.getChecksum());
    }
}