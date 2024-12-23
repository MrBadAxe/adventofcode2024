import java.util.ArrayList;
import java.util.HashMap;

public class SecretNumberMonkey{
    ArrayList<Long> secretNumbers;
    ArrayList<Long> priceList;
    ArrayList<Long> priceChangeList;
    HashMap<String,Long> bananaCounts;

    private final int SEQUENCE_LENGTH = 4;

    private static long getNextSecretNumber(long n0){
        long n1 = ((n0 << 6) ^ n0) & 0xFFFFFF;
        long n2 = ((n1 >> 5) ^ n1) & 0xFFFFFF;
        long n3 = ((n2 << 11) ^ n2) & 0xFFFFFF;
        return n3;
    }
    public SecretNumberMonkey(long init, int secretNumberCount){
        secretNumbers = new ArrayList<>();
        secretNumbers.add(init);
        long n = init;
        for(int k=0;k<secretNumberCount;k++){
            n = getNextSecretNumber(n);
            secretNumbers.add(n);
        }
    }
    public SecretNumberMonkey(long init){
        this(init,2000);
    }

    public long getSecretNumber(int index){
        return secretNumbers.get(index);
    }

    public ArrayList<Long> getPriceList(){
        if(priceList == null){
            priceList = new ArrayList<>();
            for(long n : secretNumbers){
                priceList.add(n%10);
            }
        }
        return priceList;
    }

    public ArrayList<Long> getPriceChangeList(){
        if(priceChangeList == null){
            priceChangeList = new ArrayList<>();
            for(int k=0;k<secretNumbers.size()-1;k++){
                long after = secretNumbers.get(k+1)%10;
                long before = secretNumbers.get(k)%10;
                long change = (after - before);
                priceChangeList.add(change);
            }
        }
        return priceChangeList;
    }

    public HashMap<String,Long> getBananaCounts(){
        if(bananaCounts == null){
            bananaCounts = new HashMap<>();

            for(int k=0;k<=this.getPriceChangeList().size()-SEQUENCE_LENGTH;k++){
                ArrayList<Long> priceChangeSequence = new ArrayList<>();
                for(int j=0;j<SEQUENCE_LENGTH;j++){
                    priceChangeSequence.add(priceChangeList.get(k+j));
                }
                String key = priceChangeSequence.toString();
                long value = this.getPriceList().get(k+SEQUENCE_LENGTH);
                bananaCounts.putIfAbsent(key,value);
            }
        }
        return bananaCounts;
    }
}