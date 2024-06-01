module com.example.infinitemonkeygui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.infinitemonkeygui to javafx.fxml;
    exports com.example.infinitemonkeygui;
    exports com.example.infinitemonkeygui.controllers;
    opens com.example.infinitemonkeygui.controllers to javafx.fxml;
}