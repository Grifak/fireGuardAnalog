package fire.guard.analog.fireguard;

import fire.guard.analog.fireguard.enums.Task2Substance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Task2Controller implements Initializable {
    @FXML
    private ChoiceBox<String> listTask2;
    @FXML
    private TextField molarMassField;
    @FXML
    private TextField densityField;


    //Пункт 6
    @FXML
    private TextField formulaField;
    @FXML
    private ChoiceBox<Integer> tempList;
    @FXML
    private TextField antuanA;
    @FXML
    private TextField antuanB;
    @FXML
    private TextField antuanC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listTask2.getItems().addAll(Task2Substance.getNames());
        listTask2.setOnAction(this::getSubstanceValue);

        tempList.getItems().addAll(10, 15, 20, 30, 35);
    }

    public void getSubstanceValue(ActionEvent event){
        Task2Substance substance = Task2Substance.getByName(listTask2.getValue());
        molarMassField.setText(substance.getMolarMass().toString());
        formulaField.setText(substance.getFormula());
        antuanA.setText(substance.getAntuanA().toString());
        antuanB.setText(substance.getAntuanB().toString());
        antuanC.setText(substance.getAntuanC().toString());
    }
}
