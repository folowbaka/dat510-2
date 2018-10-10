import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Cyclic {
    public static final String[] SAFE_PRIME={"5", "7", "11", "23", "47", "59", "83", "107", "167", "179", "227", "263", "347", "359", "383", "467", "479", "503", "563", "587", "719", "839", "863", "887", "983", "1019", "1187", "1283", "1307", "1319", "1367", "1439", "1487", "1523", "1619", "1823", "1907"};
    public static final int GENERATOR=2;
    public  Cyclic()
    {

    }
    public int calculateKeyPair(int privateKey,int generator,int p)
    {
        return (int)(Math.pow(generator,privateKey))%p;
    }
}
