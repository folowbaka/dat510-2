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

    private DIH dih;

    public void setData()
    {
        this.safePrimeCB.getItems().addAll(DIH.SAFE_PRIME);
        this.safePrimeCB.setValue(this.safePrimeCB.getItems().get(0));
    }

    /**
     *  Method triggered when the button "Calculate Keys pairs"  is clicked
     */
    public void calculateKeyPairs()
    {
        //Get the parameters values
        int a=Integer.parseInt(aliceATF.getText());
        int b=Integer.parseInt(bobATF.getText());
        int p=Integer.parseInt((String)safePrimeCB.getValue());
        this.dih=new DIH(p);
        //Calculate the public key for Alice and Bob
        int alicePU=dih.calculateKeyPair(a);
        int bobPU=dih.calculateKeyPair(b);
        this.alicePUTF.setText(""+alicePU);
        this.bobPUTF.setText(""+bobPU);
        //Calculate the shared key for Alice and Bob
        int aliceSharedKey=dih.calculateSharedKey(bobPU,a);
        int bobSharedKey=dih.calculateSharedKey(alicePU,b);
        //Look if each shared key are the same
        if(aliceSharedKey==bobSharedKey)
            kabTF.setText(""+aliceSharedKey);

        BBS bbs=new BBS(aliceSharedKey);
        //Generate the new key with the BBS Generator
        String k=bbs.randomKey(10);
        kTF.setText(""+(Integer.parseInt(k,2)));
        SDES sdes=new SDES();
        String plaintext=plainTextTA.getText();
        int textLength=plaintext.length();
        String cipherText="";
        //Encrypt by block with the SDES cipher
        for(int i=0;i<textLength;i++)
        {
            String character=Integer.toBinaryString(plaintext.substring(i,i+1).getBytes()[0]);
            character=sdes.fillZeroBinary(character.length(),character,8);
            cipherText+=sdes.cipher(k,character);
        }
        cipherTextTA.setText(cipherText);
        int cipherTextLength=cipherText.length();
        String decipherText="";
        //Decrypt by block the SDES ciphered text
        for(int i=0;i<cipherTextLength;i+=8)
        {
            String cipherCharacter=cipherText.substring(i,i+8);
            decipherText+=(char)Integer.parseInt(sdes.decipher(k,cipherCharacter),2);


        }
        decipherTextTA.setText(decipherText);

    }

}
