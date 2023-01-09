module com.example.clickmichtot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clickmichtot to javafx.fxml;
    exports com.example.clickmichtot;
}