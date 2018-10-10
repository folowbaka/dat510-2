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

    public void setData()
    {
        this.safePrimeCB.getItems().addAll(Cyclic.SAFE_PRIME);
        this.safePrimeCB.setValue(this.safePrimeCB.getItems().get(0));
    }
}
