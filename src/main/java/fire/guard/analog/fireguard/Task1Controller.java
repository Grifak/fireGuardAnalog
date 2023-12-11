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
    private Label welcomeText;
    @FXML
    private ChoiceBox<String> dropDownList;
    @FXML
    private TextField pressureField;
    @FXML
    private TextField volumeField;

    private final Map<String,Integer> gasMap = Map.of("Ацетилен", 26,"Бутан", 58,"Бутен", 56,"Метан", 16,"Пропилен", 42,"Этан", 30,"Этилен", 28);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (var item : gasMap.keySet()) {
            dropDownList.getItems().add(item);
        }
    }
}