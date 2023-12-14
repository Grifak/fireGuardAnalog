package fire.guard.analog.fireguard;

import fire.guard.analog.fireguard.enums.Task1Stehio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class Task1Controller implements Initializable {
    private final ValuesStorage valuesStorage = new ValuesStorage();
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
    private TextField insideTempField;
    @FXML
    private TextField outGasField;
    @FXML
    private TextField inGasField;
    @FXML
    private TextField airExchgField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField areaCoefField;
    private Double NcField;
    private Double NhField;
    @FXML
    private TextField NxField;
    @FXML
    private TextField NoField;
    @FXML
    private TextField maxPressureField;
    @FXML
    private TextField startPressureField;
    @FXML
    private ChoiceBox<String> dropDownListZ;
    @FXML
    private ChoiceBox<String> dropDownListSteh;
    @FXML
    private TextField res1;
    @FXML
    private TextField res2;
    @FXML
    private TextField res3;
    @FXML
    private TextField res4;
    @FXML
    private TextField res5;
    @FXML
    private TextField res6;
    @FXML
    private TextField res7;
    @FXML
    private TextField res8;
    @FXML
    private TextField res9;
    @FXML
    private TextField res10;
    @FXML
    private Label warningLabel;
    private final Map<String,Integer> gasMap = Map.of("Ацетилен", 26,"Бутан", 58,"Бутен", 56,"Метан", 16,"Пропилен", 42,"Этан", 30,"Этилен", 28);

    private final Double[] coefficientZ = {1.0, 0.5, 0.3, 0.0};



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(var item : gasMap.keySet()) {
            dropDownList.getItems().add(item);
        }
        for(var item : coefficientZ){
            dropDownListZ.getItems().add(String.valueOf(item));
        }
        dropDownListSteh.getItems().addAll(Task1Stehio.getNames());
        dropDownListSteh.setOnAction(this::getStehioValue);

    }

    public void getStehioValue(ActionEvent actionEvent){
        Task1Stehio stehio = Task1Stehio.getByName(dropDownListSteh.getValue());
        NcField = Double.valueOf(stehio.getNc());
        NhField = Double.valueOf(stehio.getNh());

    }

    public void fillResultingValues(){
        res1.setText(String.valueOf(gasMap.get(dropDownList.getValue())));
        res2.setText(String.valueOf(valuesStorage.getVa()));
        res3.setText(String.valueOf(valuesStorage.getV1m()));
        res4.setText(String.valueOf(valuesStorage.getV2m()));
        res5.setText(String.valueOf(valuesStorage.getRhoG()));
        res6.setText(String.valueOf(valuesStorage.getM()));
        res7.setText(String.valueOf(valuesStorage.getMstar()));
        res8.setText(String.valueOf(valuesStorage.getVsv()));
        res9.setText(String.valueOf(valuesStorage.getCsteh()));
        res10.setText(String.valueOf(valuesStorage.getDeltaP()));
    }

    public void onGetValues(ActionEvent actionEvent){
        try {
            valuesStorage.setVa(GasCalculator.calculateVa(Double.parseDouble(pressureField.getText()), Double.parseDouble(volumeField.getText())));
            valuesStorage.setV1m(GasCalculator.calculateV1m(Double.parseDouble(gasConsumptionField.getText()), Double.parseDouble(closingTimeField.getText())));
            valuesStorage.setV2m(GasCalculator.calculateV2m(Double.parseDouble(maxP2Field.getText()), Double.parseDouble(lpodvField.getText()),
                    Double.parseDouble(dodvField.getText()), Double.parseDouble(lotvField.getText()),
                    Double.parseDouble(dotvField.getText())));

            valuesStorage.setRhoG(GasCalculator.calculateRhoG(gasMap.get(dropDownList.getValue()), Double.parseDouble(insideTempField.getText())));
            valuesStorage.setM(GasCalculator.calculateM(Double.parseDouble(inGasField.getText()), Double.parseDouble(outGasField.getText()), valuesStorage.getRhoG()));
            valuesStorage.setMstar(GasCalculator.calculateMStar(valuesStorage.getM(), Double.parseDouble(airExchgField.getText()), Double.parseDouble(closingTimeField.getText())));
            valuesStorage.setCoefZ(Double.parseDouble(dropDownListZ.getValue()));
            valuesStorage.setVsv(GasCalculator.calculateVsv(Double.parseDouble(lengthField.getText()), Double.parseDouble(widthField.getText()),
                    Double.parseDouble(heightField.getText()), Double.parseDouble(areaCoefField.getText())));

            valuesStorage.setCsteh(GasCalculator.calculateCsteh(NcField, NhField, Double.parseDouble(NxField.getText()), Double.parseDouble(NoField.getText())));

            valuesStorage.setDeltaP(GasCalculator.calculateDeltaP(Double.parseDouble(maxPressureField.getText()),
                    Double.parseDouble(startPressureField.getText()), valuesStorage.getMstar(),
                    valuesStorage.getCoefZ(), valuesStorage.getVsv(), valuesStorage.getRhoG(),
                    valuesStorage.getCsteh(), Double.valueOf(valuesStorage.getKn())));

            fillResultingValues();
        }
        catch (ArithmeticException arithmeticException){
            arithmeticException.getCause();
            warningLabel.setText("Ошибка в веденных значениях! Попробуйте еще раз");
        }

    }


    public void onGenerateReport(ActionEvent actionEvent) {
    }
}