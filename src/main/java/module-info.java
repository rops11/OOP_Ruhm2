module com.oop.ruhm2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.ruhm2 to javafx.fxml;
    exports com.oop.ruhm2;
}