module com.noble_coding.patateman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.noble_coding.patateman to javafx.fxml;
    exports com.noble_coding.patateman;
}