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
    @FXML
    private TextField formulaField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listTask2.getItems().addAll(Task2Substance.getNames());
        listTask2.setOnAction(this::getSubstanceValue);
    }

    public void getSubstanceValue(ActionEvent event){
        Task2Substance substance = Task2Substance.getByName(listTask2.getValue());
        molarMassField.setText(substance.getMolarMass().toString());
        formulaField.setText(substance.getFormula());
    }
}
