import java.util.List;

public class Day09{
    private static int[] generateDiskFromMap(String diskMap){
        int diskSize = 0;
        for(int k=0;k<diskMap.length();k++){
            diskSize += (diskMap.charAt(k) - '0');
        }
        int[] z = new int[diskSize];
        for(int k=0;k<diskSize;k++){
            z[k] = -1;
        }
        int index = 0;
        for(int k=0;k<diskMap.length();k++){
            int size = (diskMap.charAt(k) - '0');
            if(k%2==0){
                int fileId = k/2;
                for(int j=0;j<size;j++){
                    z[index] = fileId;
                    index++;
                }    
            }else{
                index += size;
            }
        }
        return z;
    }
    private static int[] compressDisk(int[] disk){
        int[] newDisk = disk;
        int nextSpaceIndex = 0;
        int lastFileIndex = disk.length - 1;
        while(lastFileIndex > 0 && nextSpaceIndex < newDisk.length && lastFileIndex > nextSpaceIndex){
            if(newDisk[nextSpaceIndex] != -1){
                nextSpaceIndex++;
            }else if(newDisk[lastFileIndex] == -1){
                lastFileIndex--;
            }else{
                newDisk[nextSpaceIndex] = newDisk[lastFileIndex];
                newDisk[lastFileIndex] = -1;
            }
        }
        return newDisk;
    }
    private static String diskToString(int[] disk){
        StringBuilder z = new StringBuilder("(");
        for(int k=0;k<disk.length;k++){
            z.append(disk[k]);
            if(k < disk.length-1){
                z.append(",");
            }
        }
        z.append(")");
        return z.toString();
    }
    public static String getPart01(List<String> input){
        String diskMap = input.get(0);
        int[] disk = generateDiskFromMap(diskMap);
        int[] newDisk = compressDisk(disk);
        long total = 0;
        for(int k=0;newDisk[k]!=-1;k++){
            total += k * newDisk[k];
        }
        
        return Long.toString(total);
    }
}