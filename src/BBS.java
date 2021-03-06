import java.math.BigInteger;

public class BBS {
    private int seed;
    public static final int P=2011;
    public static final int Q=1787;
    private int m;

    public BBS(int seed)
    {
        this.seed=seed;
        this.m=P*Q;
    }

    /**
     * Calculate one bit of the key by taking the least significant bit of the equation's result
     * @return
     */
    public String randomBits()
    {
        BigInteger seed=new BigInteger(""+this.seed);
        seed=seed.pow(2);
        seed=seed.mod(new BigInteger(""+this.m));
        this.seed=seed.intValue();
        String binary=Integer.toBinaryString(this.seed);
        int nbBits=binary.length();
        return binary.substring(nbBits-1);
    }

    /**
     * Generate the key by generating  keyLength bits
     * @param keyLength
     * @return
     */
    public String randomKey(int keyLength)
    {
        String randomKey="";
        for(int i=0;i<keyLength;i++)
        {
            randomKey+=randomBits();
        }
        return randomKey;
    }
}
