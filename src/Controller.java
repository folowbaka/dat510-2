import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.SDES;
import sample.Vigenere;

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
    @FXML
    private TextField kabTF;
    @FXML
    private TextField kTF;
    @FXML
    private TextArea plainTextTA;
    @FXML
    private TextArea cipherTextTA;
    @FXML
    private TextArea decipherTextTA;
    private Cyclic cyclic;

    public void setData()
    {
        this.safePrimeCB.getItems().addAll(Cyclic.SAFE_PRIME);
        this.safePrimeCB.setValue(this.safePrimeCB.getItems().get(0));
    }

    public void calculateKeyPairs()
    {
        System.out.println(Integer.toBinaryString(1));
        int a=Integer.parseInt(aliceATF.getText());
        int b=Integer.parseInt(bobATF.getText());
        int p=Integer.parseInt((String)safePrimeCB.getValue());
        this.cyclic=new Cyclic(p);
        int alicePU=cyclic.calculateKeyPair(a);
        int bobPU=cyclic.calculateKeyPair(b);
        this.alicePUTF.setText(""+alicePU);
        this.bobPUTF.setText(""+bobPU);
        int aliceSharedKey=cyclic.calculateSharedKey(bobPU,a);
        int bobSharedKey=cyclic.calculateSharedKey(alicePU,b);
        if(aliceSharedKey==bobSharedKey)
            kabTF.setText(""+aliceSharedKey);

        BBS bbs=new BBS(aliceSharedKey);
        String k=bbs.randomKey(10);
        System.out.println(k);
        kTF.setText(k);
        SDES sdes=new SDES();
        String plaintext=plainTextTA.getText();
        int textLength=plaintext.length();
        String cipherText="";
        for(int i=0;i<textLength;i++)
        {
            String character=Integer.toBinaryString(plaintext.substring(i,i+1).getBytes()[0]);
            character=sdes.fillZeroBinary(character.length(),character,8);
            cipherText+=sdes.cipher(k,character);
        }
        cipherTextTA.setText(cipherText);
        int cipherTextLength=cipherText.length();
        String decipherText="";
        for(int i=0;i<cipherTextLength;i+=8)
        {
            String cipherCharacter=cipherText.substring(i,i+8);
            decipherText+=(char)Integer.parseInt(sdes.decipher(k,cipherCharacter),2);


        }
        decipherTextTA.setText(decipherText);

    }

}
