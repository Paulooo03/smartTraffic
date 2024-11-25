module com.example.smarttraffic {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.smarttraffic to javafx.fxml;
    exports com.example.smarttraffic;
}