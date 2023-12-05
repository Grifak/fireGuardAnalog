module fire.guard.analog.fireguard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens fire.guard.analog.fireguard to javafx.fxml;
    exports fire.guard.analog.fireguard;
}