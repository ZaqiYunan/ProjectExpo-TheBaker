module com.warkop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    


    opens com.warkop to javafx.fxml;
    exports com.warkop;
}
