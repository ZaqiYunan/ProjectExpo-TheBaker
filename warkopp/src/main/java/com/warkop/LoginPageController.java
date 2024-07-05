package com.warkop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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

    private SessionManager sessionManager;

    public void initialize() {
        sessionManager = SessionManager.getInstance();
    }

    public void handlelabelCaptchaAction() {
        if (tombolCaptcha.isSelected()) {
            labelCaptcha.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            labelCaptcha.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        }
    }

    public void handleSignInButtonAction() {
        // Validate user input, authenticate user
        String email = inputEmail.getText();
        String password = inputPassword.getText();
        boolean captchaChecked = tombolCaptcha.isSelected();

        if (email.isEmpty() || password.isEmpty() || !captchaChecked) {
            errorLabel.setText("Harap isi semua kolom dan centang captcha!");
            return;
        }

        User authenticatedUser = authenticateUser(email, password);

        if (authenticatedUser != null) {
            // Update session variables
            sessionManager.loginUser(authenticatedUser.getUserID(), authenticatedUser);
            moveToNextScene(authenticatedUser.getRole());
        } else {
            // Handle invalid credentials
            errorLabel.setText("Email atau password salah!");
        }
    }

    public void moveToNextScene(String userRole) {
        // Move to the next scene based on user's role or default page
        Platform.runLater(() -> {
            try {
                if (userRole.contains("Pembeli Materi Dasar")) {
                    App.setRoot("MateriDasarPage"); // Example page for buyers of basic material
                } else {
                    App.setRoot("BerandaPage"); // Default next page after login
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean isAuthorized(String userID) {
        List<String> allowedRoles = Arrays.asList("Pembeli Materi Dasar", "Pembeli Materi Menengah",
                "Pembeli Materi Lanjut");
        try {
            File jsonFile = new File("DataUser.json"); // Adjust path as necessary
            if (jsonFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    String userId = jsonObject.get("userID").getAsString();
                    if (userId.equals(userID)) {
                        String userRole = jsonObject.get("role").getAsString();
                        return allowedRoles.contains(userRole);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Default to false if user not found or role not in allowedRoles
    }

    private User authenticateUser(String email, String password) {
        try {
            URL resourceUrl = getClass().getResource("DataUser.json");
            if (resourceUrl != null) {
                File jsonFile = new File(resourceUrl.toURI());
                try (BufferedReader br = new BufferedReader(new FileReader(jsonFile))) {
                    JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        String csvEmail = jsonObject.get("email").getAsString();
                        String csvPassword = jsonObject.get("password").getAsString();

                        if (email.equals(csvEmail) && password.equals(csvPassword)) {
                            // Construct User object
                            User user = new User(
                                    jsonObject.get("userID").getAsString(),
                                    jsonObject.get("role").getAsString(),
                                    jsonObject.get("namaLengkap").getAsString(),
                                    email,
                                    password);
                            return user;
                        }
                    }
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void handleRegistrasiButtonAction() {
        try {
            App.setTitle("Warkop - RegistrasiPage");
            App.setRoot("RegistrasiPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
