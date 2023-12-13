package fire.guard.analog.fireguard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class Task1Controller implements Initializable {

    @FXML
    private ChoiceBox<String> dropDownList;
    @FXML
    private TextField pressureField;
    @FXML
    private TextField volumeField;
    @FXML
    private TextField gasConsumptionField;
    @FXML
    private TextField closingTimeField;
    @FXML
    private TextField maxP2Field;
    @FXML
    private TextField lpodvField;
    @FXML
    private TextField dodvField;
    @FXML
    private TextField lotvField;
    @FXML
    private TextField dotvField;
    @FXML
    private TextField molarVolumeField;
    @FXML
    private TextField insideTempField;
    @FXML
    private TextField outGasField;
    @FXML
    private TextField insideTempField1;
    @FXML
    private TextField airExchgField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField lengthField1;
    @FXML
    private TextField widthField1;
    @FXML
    private TextField maxPressureField;
    @FXML
    private TextField startPressureField;
    @FXML
    private ChoiceBox<String> dropDownListZ;
    @FXML
    private ChoiceBox<String> dropDownListSteh;
    @FXML
    private Label warningLabel;
    private final Map<String,Integer> gasMap = Map.of("Ацетилен", 26,"Бутан", 58,"Бутен", 56,"Метан", 16,"Пропилен", 42,"Этан", 30,"Этилен", 28);

    private final Float[] coefficientZ = {1.0F, 0.5F, 0.3F, 0.0F};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (var item : gasMap.keySet()) {
            dropDownList.getItems().add(item);
        }
        for(var item : coefficientZ){
            dropDownListZ.getItems().add(String.valueOf(item));
        }
    }

    public void onGetValues(){
        try {
            System.out.println(pressureField.getText());
            System.out.println(volumeField.getText());
            System.out.println(gasMap.get(dropDownList.getValue()));
        }
        catch(Exception e){
            e.getCause();
        }

    }
}