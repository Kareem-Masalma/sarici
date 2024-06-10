package com.sarisi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btLogIn;
    @FXML
    private PasswordField pfPassword;


    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/sarisi";

    //  Database credentials
    String USER = "root";
    String PASS = "kareem2004";
    String port = "3306";
    String host = "localhost";
    Connection con;
    String dbURL = "jdbc:mysql://" + host + ":" + port + "/sarisi" + "?verifyServerCertificate=false";
    Properties p = new Properties();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        p.setProperty("user", USER);
        p.setProperty("password", PASS);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        try {
            con = DriverManager.getConnection(dbURL, p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btLogIn.setOnAction(this::logIn);
        pfPassword.setOnAction(this::logIn);
    }

    /* A stage to ask the user to enter the password to the system */
    private void logIn(ActionEvent event) {
        try {

            String query = "SELECT * FROM manager";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String password = rs.getString("passwd");
                if (pfPassword.getText().equals(password)) {
                    ((Stage) btLogIn.getScene().getWindow()).close();
                    FXMLLoader loader = new FXMLLoader(new File("Main.fxml").toURI().toURL());
                    Pane root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Sarisi");

                    stage.show();

                } else {
                    Stage stage = new Stage();
                    Label root = new Label("  Access Denied!!!\nIncorrect password");
                    root.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(root, 300, 100);
                    stage.setScene(scene);
                    stage.setTitle("Error");
                    stage.setResizable(false);
                    stage.show();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing query");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}