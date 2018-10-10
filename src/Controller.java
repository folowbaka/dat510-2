import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ComboBox safePrimeCB;
    @FXML
    private TextField aliceATF;
    @FXML
    private TextField bobATF;
    @FXML
    private TextField alicePUTF;
    @FXML
    private TextField bobPUTF;

    private Cyclic cyclic;

    public void setData()
    {
        this.safePrimeCB.getItems().addAll(Cyclic.SAFE_PRIME);
        this.safePrimeCB.setValue(this.safePrimeCB.getItems().get(0));
    }

    public void calculateKeyPairs()
    {
        int a=Integer.parseInt(aliceATF.getText());
        int b=Integer.parseInt(bobATF.getText());
        int p=Integer.parseInt((String)safePrimeCB.getValue());
        this.cyclic=new Cyclic();
        int alicePU=cyclic.calculateKeyPair(a,Cyclic.GENERATOR,p);
        int bobPU=cyclic.calculateKeyPair(b,Cyclic.GENERATOR,p);
        this.alicePUTF.setText(""+alicePU);
        this.bobPUTF.setText(""+bobPU);



    }

}
