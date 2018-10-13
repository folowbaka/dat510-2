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

    public String randomBits()
    {
        this.seed=(int)Math.pow(this.seed,2)%m;
        String binary=Integer.toBinaryString(this.seed);
        int nbBits=binary.length();
        return binary.substring(nbBits-1);
    }
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
