import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.math.BigInteger;

public class DIH {
    public static final String[] SAFE_PRIME={"5", "7", "11", "23", "47", "59", "83", "107", "167", "179", "227", "263", "347", "359", "383", "467", "479", "503", "563", "587", "719", "839", "863", "887", "983", "1019", "1187", "1283", "1307", "1319", "1367", "1439", "1487", "1523", "1619", "1823", "1907"};
    public static final int GENERATOR=2;
    private int p;
    public  DIH(int p)
    {
        this.p=p;
    }

    /**
     * Calculate the public key
     * @param privateKey
     * @return
     */
    public int calculateKeyPair(int privateKey)
    {
        BigInteger key=new BigInteger(""+GENERATOR);
        key=key.pow(privateKey);
        key=key.mod(new BigInteger(""+this.p));
        return key.intValue();
    }

    /**
     * Calculate the shared key
     * @param publicKey
     * @param privateKey
     * @return
     */
    public int calculateSharedKey(int publicKey,int privateKey)
    {
        BigInteger key=new BigInteger(""+publicKey);
        key=key.pow(privateKey);
        key=key.mod(new BigInteger(""+this.p));
        return key.intValue();
    }
}
