package com.warkop;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ScrollPane;

public class SertifikasiPageController {
    @FXML
    private Button tombolMateriPelatihan;

    @FXML
    private Button tombolHome;

    @FXML 
    private Button tombolKalenderInteraktif;

    @FXML
    private Button tombolChatBox;

    @FXML 
    private Button tombolKeluar;
    @FXML
    private Label labelMateriPelatihan;
    @FXML
    private Label labelChatBox;
    @FXML
    private Label labelKalenderInteraktif;
    @FXML
    private Label labelHome;
    private Scene scene;
    
    public void initialize(){
        tombolChatBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelChatBox.setStyle("-fx-underline: true"));
        tombolChatBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelChatBox.setStyle("-fx-underline: false"));
        tombolKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelKalenderInteraktif.setStyle("-fx-underline: true"));
        tombolKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelKalenderInteraktif.setStyle("-fx-underline: false"));
        tombolMateriPelatihan.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelMateriPelatihan.setStyle("-fx-underline: true"));
        tombolMateriPelatihan.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelMateriPelatihan.setStyle("-fx-underline: false"));
        tombolHome.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelHome.setStyle("-fx-underline: true"));
        tombolHome.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelHome.setStyle("-fx-underline: false"));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> tombolKeluar.setStyle("-fx-background-color:#ff8d7b "));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_EXITED, event -> tombolKeluar.setStyle("-fx-background-color: #ff2200"));
    }
    
    public void handleHomeAction(){
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
        
    }
    public void handleKeluarAction(){
        new Thread(() -> {
            try {
                Thread.sleep(0200); // Delay for 0.2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Show the new scene in the JavaFX thread
            Platform.runLater(() -> {
                try {
                    App.setRoot("LoginPage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }
    public void handleKalenderInteraktifAction(){
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
    }
    public void handleChatboxAction(){
        
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
    }
    public void handleMateriPelatihanAction(){
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
    }
}
