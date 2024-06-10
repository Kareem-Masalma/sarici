package com.sarisi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Driver extends Application {

    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            this.stage = stage;
            FXMLLoader loader = new FXMLLoader(new File("Log.fxml").toURI().toURL());
            Pane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}