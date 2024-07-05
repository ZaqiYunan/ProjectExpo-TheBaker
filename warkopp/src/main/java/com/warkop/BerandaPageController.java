package com.warkop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Platform;
import javafx.fxml.FXML;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.ScrollPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class BerandaPageController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Rectangle rectangleSertifikasi;
    @FXML
    private Button kelasVirtualButton;
    @FXML
    private Button tombolSertifikasi;
    @FXML
    private Button tombolMateriPelatihan;
    @FXML
    private Button tombolKalenderInteraktif;
    @FXML
    private Button tombolChatBox;
    @FXML
    private Button tombolMulaiDariSini;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Rectangle rectangleKeluar;

    @FXML
    private Button tombolKeluar;

    @FXML
    private Label labelSertifikasi;

    @FXML
    private Label labelMateriPelatihan;

    @FXML
    private Label labelKalenderInteraktif;

    @FXML
    private Label labelNamaAkun;

    @FXML
    private Label labelChatBox;

    @FXML
    private Label labelProgresAnda;

    @FXML
    private Label labelDasar;

    @FXML
    private Label labelMenengah;

    @FXML
    private Label labelLanjut;

    @FXML
    private ProgressBar progressBarDasar;

    @FXML
    private ProgressBar progressBarMenengah;

    @FXML
    private ProgressBar progressBarLanjut;

    @FXML
    private Button tombolMateriDasar1;

    @FXML
    private Shape ktkMulaiDariSini;

    @FXML
    private Button tombolMateriDasar;

    @FXML
    private Button tombolMateriMenengah;

    @FXML
    private Button tombolMateriLanjut;

    @FXML
    private Shape rectangleMateriDasar;

    @FXML
    private Shape rectangleMateriMenengah;

    @FXML
    private Shape rectangleMateriLanjut;

    

    public void initialize() {
        loadUserData();
        
        kelasVirtualButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> kelasVirtualButton.setStyle("-fx-background-color: #527aff"));
        kelasVirtualButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> kelasVirtualButton.setStyle("-fx-background-color: #3362ff"));
        tombolChatBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelChatBox.setStyle("-fx-underline: true"));
        tombolChatBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelChatBox.setStyle("-fx-underline: false"));
        tombolKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> labelKalenderInteraktif.setStyle("-fx-underline: true"));
        tombolKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> labelKalenderInteraktif.setStyle("-fx-underline: false"));
        tombolMateriPelatihan.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> labelMateriPelatihan.setStyle("-fx-underline: true"));
        tombolMateriPelatihan.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> labelMateriPelatihan.setStyle("-fx-underline: false"));
        tombolSertifikasi.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> labelSertifikasi.setStyle("-fx-underline: true"));
        tombolSertifikasi.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> labelSertifikasi.setStyle("-fx-underline: false"));
        tombolMulaiDariSini.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> ktkMulaiDariSini.setStyle("-fx-fill: #38cc80"));
        tombolMulaiDariSini.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> ktkMulaiDariSini.setStyle("-fx-fill: #4cf2cb"));
        tombolMulaiDariSini.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleMulaiDariSiniAction());
        tombolMateriDasar.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> rectangleMateriDasar.setStyle("-fx-fill: #23c9c3"));
        tombolMateriDasar.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> rectangleMateriDasar.setStyle("-fx-fill: #2cfffb"));
        tombolMateriMenengah.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> rectangleMateriMenengah.setStyle("-fx-fill: #5ed347"));
        tombolMateriMenengah.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> rectangleMateriMenengah.setStyle("-fx-fill: #6fff55"));
        tombolMateriLanjut.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> rectangleMateriLanjut.setStyle("-fx-fill: #cc6d2d"));
        tombolMateriLanjut.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> rectangleMateriLanjut.setStyle("-fx-fill: #ff8736"));
    }

    private boolean isLoggedIn = true;
    private String userRole = "";
    private String userID;
    private void loadUserData() {
        try {
            URL resourceUrl = getClass().getResource("DataUser.json");
            if (resourceUrl != null) {
                File jsonFile = new File(resourceUrl.toURI());
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();

                // Find the user in the JSON array based on userID
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    String csvUserID = jsonObject.get("userID").getAsString();

                    if (csvUserID.equals(userID)) {
                        String namaLengkap = jsonObject.get("namaLengkap").getAsString();
                        labelNamaAkun.setText(namaLengkap);
                        break;
                    }
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private void upgradeUserRole(String userID, String newRole) {
        try {
            URL resourceUrl = getClass().getResource("DataUser.json");
            if (resourceUrl != null) {
                File jsonFile = new File(resourceUrl.toURI());
                BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                br.close();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    String csvUserID = jsonObject.get("userID").getAsString();
                    if (csvUserID.equals(userID)) {
                        jsonObject.addProperty("role", newRole);
                        break;
                    }
                }

                // Write the updated JSON back to the file
                FileWriter writer = new FileWriter(jsonFile);
                writer.write(jsonArray.toString());
                writer.close();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void handleBeliMateriDasar() {
        if (isLoggedIn) {
            try {
                URL resourceUrl = getClass().getResource("DataUser.json");
                if (resourceUrl != null) {
                    File jsonFile = new File(resourceUrl.toURI());
                    if (!jsonFile.exists()) {
                        showErrorPopup("Error", "File not found", "DataUser.json file does not exist.");
                        return;
                    }
    
                    BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                    JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
                    br.close();
    
                    boolean userFound = false;
    
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        String csvUserID = jsonObject.get("userID").getAsString();
                        String csvUserRole = jsonObject.get("role").getAsString();
    
                        if (csvUserID.contains("G") || csvUserID.contains("A")) {
                            userFound = true;
    
                            if (!csvUserRole.equals("Pembeli Materi Dasar")) {
                                jsonObject.addProperty("role", "Pembeli Materi Dasar");
    
                                // Write the updated JSON back to the file
                                FileWriter writer = new FileWriter(jsonFile);
                                writer.write(jsonArray.toString());
                                writer.flush();
                                writer.close();
    
                                showErrorPopup("Pembelian Berhasil", "Anda telah berhasil membeli paket Materi Dasar",
                                        "Silahkan cek Materi Pelatihan untuk memulai belajar");
                            } else {
                                showErrorPopup("Error", "Update Failed", "User already has the role 'Pembeli Materi Dasar'.");
                            }
                        }
                    }
    
                    if (!userFound) {
                        showErrorPopup("Error", "User Not Found", "No users with 'G' or 'A' in their ID were found in the JSON file.");
                    }
                } else {
                    showErrorPopup("Error", "Resource Not Found", "DataUser.json resource could not be found.");
                }
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
                showErrorPopup("Error", "Failed to upgrade user role", e.getMessage());
            }
        } else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan login terlebih dahulu untuk membeli paket Materi Dasar");
        }
    }
    
    
    
    

    public void handleMulaiDariSiniAction() {
        scrollPane.setVvalue(scrollPane.getVvalue() + 0.5);
    }

    public void handleSertifikasiAction() {
        if (isLoggedIn || !userRole.contains("Guest")||userRole.contains("Pembeli Materi Dasar")||userRole.contains("Pembeli Materi Menengah")||userRole.contains("Pembeli Materi Lanjut"))
            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.setRoot("SertifikasiPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan membeli paket pelatihan untuk mengakses fitur ini");
        }

    }

    public void handleMateriPelatihanAction() {
        if (isLoggedIn || !userRole.contains("Guest")||userRole.contains("Pembeli Materi Dasar")||userRole.contains("Pembeli Materi Menengah")||userRole.contains("Pembeli Materi Lanjut"))
            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.setRoot("MateriPelatihanPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan membeli paket pelatihan untuk mengakses fitur ini");
        }
    }

    public void handleKalenderInteraktifAction() {
        if (isLoggedIn || !userRole.contains("Guest")||userRole.contains("Pembeli Materi Dasar")||userRole.contains("Pembeli Materi Menengah")||userRole.contains("Pembeli Materi Lanjut"))
            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.showKalenderInteraktifPage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan membeli paket pelatihan untuk mengakses fitur ini");
        }

    }

    private Parent loadFXML(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public void handleChatboxAction() {
        if (isLoggedIn || !userRole.contains("Guest")||userRole.contains("Pembeli Materi Dasar")||userRole.contains("Pembeli Materi Menengah")||userRole.contains("Pembeli Materi Lanjut"))
            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.setRoot("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan membeli paket pelatihan untuk mengakses fitur ini");
        }
    }

    public void handleKelasVirtualAction() {
        if (isLoggedIn || !userRole.contains("Guest")||userRole.contains("Pembeli Materi Dasar")||userRole.contains("Pembeli Materi Menengah")||userRole.contains("Pembeli Materi Lanjut"))
            new Thread(() -> {
                try {
                    Thread.sleep(0200); // Delay for 0.2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Show the new scene in the JavaFX thread
                Platform.runLater(() -> {
                    try {
                        App.setRoot("KelasVirtualPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        else {
            showErrorPopup("Akses Ditolak", "Anda tidak memiliki izin untuk mengakses fitur ini",
                    "Silahkan membeli paket pelatihan untuk mengakses fitur ini");
        }
    }

    public void handleKeluarAction() {
        // Clear session variables
        isLoggedIn = false;
        userRole = "";
        userID = "";
    
        // Proceed to login page
        new Thread(() -> {
            try {
                Thread.sleep(200); // Delay for 0.2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Show the login scene in the JavaFX thread
            Platform.runLater(() -> {
                try {
                    App.setRoot("LoginPage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }
    

    public void handleKeluarActionHover() {
        rectangleKeluar.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> rectangleKeluar.setStyle("-fx-background-color: #ff8d7b"));
        rectangleKeluar.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> rectangleKeluar.setStyle("-fx-background-color:  #ff2200"));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> tombolKeluar.setStyle("-fx-background-color:#ff8d7b "));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> tombolKeluar.setStyle("-fx-background-color: #ff2200"));

    }

    private void showErrorPopup(String title, String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    // Method to handle 'Mulai dari sini!' button click
    public void handleMateriDasar1Action() {
        // Implement action for starting from here
        System.out.println("Mulai dari sini button clicked!");
    }

    // Add other methods to handle different actions
}
