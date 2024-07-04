package com.warkop;

import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.application.Platform;

public class RegistrasiPageController {

    @FXML
    private TextField inputNamaLengkap;

    @FXML
    private TextField inputEmail;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField inputPassword;

    @FXML
    private Button tombolRegistrasi;

    @FXML
    private Button tombolMasuk;

    private Stage primaryStage;

    @FXML
    public void initialize() {
        // Initialize any necessary components or set up event handlers here
    }

    @FXML
    public void handleRegistrasiButtonAction() throws URISyntaxException {
        String namaLengkap = inputNamaLengkap.getText();
        String email = inputEmail.getText();
        String password = inputPassword.getText();

        if (namaLengkap.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Silahkan isi semua kolom!");
            return;
        }
        String userID = generateUniqueID("G");
        try {
            tombolRegistrasi.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            URL resourceUrl = getClass().getResource("");
            File resourceFolder = new File(resourceUrl.toURI());
            File jsonFile = new File(resourceFolder, "DataUser.json");

            JsonArray jsonArray = new JsonArray();

            if (jsonFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();
            }

            JsonObject newUser = new JsonObject();
            newUser.addProperty("userID", userID);
            newUser.addProperty("role", "Guest");
            newUser.addProperty("namaLengkap", namaLengkap);
            newUser.addProperty("email", email);
            newUser.addProperty("password", password);

            jsonArray.add(newUser);

            FileWriter writer = new FileWriter(jsonFile);
            writer.write(jsonArray.toString());
            writer.flush();
            writer.close();

            App.setTitle("Warkop - LoginPage");
            App.setRoot("LoginPage");
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + e.getMessage());
        }
    }

    private String generateUniqueID(String prefix) throws URISyntaxException {
        int id = 1;
        try {
            URL resourceUrl = getClass().getResource("");
            File resourceFolder = new File(resourceUrl.toURI());
            File jsonFile = new File(resourceFolder, "DataUser.json");

            if (jsonFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    String userId = jsonObject.get("userID").getAsString();
                    if (userId.startsWith(prefix)) {
                        int currentId = Integer.parseInt(userId.substring(1));
                        if (currentId >= id) {
                            id = currentId + 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
        return prefix + String.format("%04d", id);
    }

    @FXML
    public void handleMasukButtonAction() {
        tombolMasuk.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Platform.runLater(() -> {
            try {
                App.setTitle("Warkop - LoginPage");
                App.setRoot("LoginPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
