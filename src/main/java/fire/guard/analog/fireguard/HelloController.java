package fire.guard.analog.fireguard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(textField.getText());
    }
}