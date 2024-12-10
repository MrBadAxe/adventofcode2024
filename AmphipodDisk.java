public class AmphipodDisk{
    private final int SIZE;
    private int[] disk;

    public AmphipodDisk(String diskMap){
        int diskSize = 0;
        for(int k=0;k<diskMap.length();k++){
            diskSize += (diskMap.charAt(k) - '0');
        }
        SIZE = diskSize;

        disk = new int[SIZE];
        for(int k=0;k<SIZE;k++){
            disk[k] = -1;
        }

        int index = 0;
        for(int k=0;k<diskMap.length();k++){
            int chunkSize = (diskMap.charAt(k) - '0');
            if(k%2==0){
                int fileId = k/2;
                for(int j=0;j<chunkSize;j++){
                    disk[index] = fileId;
                    index++;
                }    
            }else{
                index += chunkSize;
            }
        }
    }
    public String toString(){
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
    public void compressDisk(){
        int nextSpaceIndex = 0;
        int lastFileIndex = disk.length - 1;
        while(lastFileIndex > 0 && nextSpaceIndex < this.SIZE && lastFileIndex > nextSpaceIndex){
            if(disk[nextSpaceIndex] != -1){
                nextSpaceIndex++;
            }else if(disk[lastFileIndex] == -1){
                lastFileIndex--;
            }else{
                disk[nextSpaceIndex] = disk[lastFileIndex];
                disk[lastFileIndex] = -1;
            }
        }
    }
    public long getChecksum(){
        long total = 0;
        for(int k=0;k<this.SIZE;k++){
            if(disk[k] != -1){
                total += k * disk[k];
            }
        }
        return total;
    }
    public int findSpace(int size){
        int currentSpace = 0;
        for(int k=0;k<this.SIZE;k++){
            if(disk[k] != -1){
                currentSpace = 0;
            }else{
                currentSpace++;
                if(currentSpace == size){
                    return (k-size)+1;
                }
            }
        }
        return -1;
    }
    public void moveFile(int from, int to){
        int id = disk[from];
        int offset = 0;
        while(from+offset < disk.length && disk[from+offset] == id){
            disk[to+offset] = disk[from+offset];
            disk[from+offset] = -1;
            offset++;
        }
    }
    public int findFileById(int id){
        for(int k=0;k<disk.length;k++){
            if(disk[k] == id){
                return k;
            }
        }
        return -1;
    }
    public void compressDiskUnfragmented(String diskMap){
        int fileId = (diskMap.length() - 1) / 2;
        for(int k=fileId;k>=0;k--){
            int fileStart = findFileById(k);
            int fileEnd = fileStart;
            while(fileEnd < disk.length && disk[fileEnd] == k){ fileEnd++; }
            int fileSize = fileEnd - fileStart;
            int spaceStart = findSpace(fileSize);
            if(spaceStart != -1 && spaceStart < fileStart){
                moveFile(fileStart, spaceStart);
            }
        }
    }
}