module com.qpros.controller.qprosassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires json.simple;
    requires com.google.gson;

    opens com.qpros.controller to javafx.fxml;
    opens com.qpros.models to com.google.gson;

    exports com.qpros.controller;
    exports com.qpros.models;
    exports com.qpros.utils;
    exports com.qpros.connectors;
}