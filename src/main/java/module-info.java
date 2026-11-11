module com.mycompany.buzinice {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.buzinice to javafx.fxml;
    exports com.mycompany.buzinice;
}
