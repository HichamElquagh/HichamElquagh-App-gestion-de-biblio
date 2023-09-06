module com.example.appgestiondebiblio {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.appgestiondebiblio to javafx.fxml;
    exports com.example.appgestiondebiblio;
}