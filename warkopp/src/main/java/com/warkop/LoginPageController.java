package com.warkop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginPageController {
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private CheckBox tombolCaptcha;
    @FXML
    private Button tombolMasuk;
    @FXML
    private Label errorLabel;
    @FXML
    private Label labelCaptcha;
    @FXML
    private Button tombolRegistrasi;
    @FXML
    private Stage primaryStage;
    @FXML
    private Scene scene;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    @FXML
    public void handlelabelCaptchaAction() {
        if (tombolCaptcha.isSelected()) {
            labelCaptcha.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            labelCaptcha.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        }
    }

    @FXML
    public void handleSignInButtonAction() {
        // Validate user input
        String email = inputEmail.getText();
        String password = inputPassword.getText();
        boolean captchaChecked = tombolCaptcha.isSelected();

        if (email.isEmpty() || password.isEmpty() || !captchaChecked) {
            errorLabel.setText("Harap isi semua kolom dan centang captcha!");
            return;
        }

        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            tombolMasuk.setStyle("-fx-background-color: green; -fx-text-fill: white;");

            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.setRoot("BerandaPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        } else {
            errorLabel.setText("Email atau password salah!");
        }
    }

    private boolean authenticateUser(String email, String password) {
        try {
            URL resourceUrl = getClass().getResource("DataUser.json");
            if (resourceUrl != null) {
                File jsonFile = new File(resourceUrl.toURI());
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    String csvEmail = jsonObject.get("email").getAsString();
                    String csvPassword = jsonObject.get("password").getAsString();
                    if (email.equals(csvEmail) && password.equals(csvPassword)) {
                        return true;
                    }
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    public void handleRegistrasiButtonAction() {
        tombolRegistrasi.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Platform.runLater(() -> {
            try {
                App.setTitle("Warkop - RegistrasiPage");
                App.setRoot("RegistrasiPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
