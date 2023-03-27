module com.example.prototypadeetui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prototypadeetui to javafx.fxml;
    exports com.example.prototypadeetui;
}