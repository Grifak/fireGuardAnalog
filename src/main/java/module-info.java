

module fire.guard.analog.fireguard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires docx4j;


    opens fire.guard.analog.fireguard to javafx.fxml;
    exports fire.guard.analog.fireguard;
    exports fire.guard.analog.fireguard.calculator;
    opens fire.guard.analog.fireguard.calculator to javafx.fxml;
}