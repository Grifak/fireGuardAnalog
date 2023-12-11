package fire.guard.analog.fireguard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Task1Controller implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private ChoiceBox<String> choiceBox;

    private final String[] items = {"Ацетилен", "Бутан","Бутен","Метан","Пропилен","Этан","Этилен"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}