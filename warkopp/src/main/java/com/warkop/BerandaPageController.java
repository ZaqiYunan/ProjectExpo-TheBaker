package com.warkop;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;


public class BerandaPageController {
    @FXML
    private Button tombolSertifikasi;
    @FXML
    private Button tombolMateriPelatihan;
    @FXML
    private Button tombolKalenderInteraktif;
    @FXML
    private Button tombolChatbox;

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

    public void initialize(){
       
        
        
      




    }   
    public void handleSertifikasiAction(){
        labelSertifikasi.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelSertifikasi.setStyle("-fx-underline"));
        labelSertifikasi.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelSertifikasi.setStyle(""));
    }
    public void handleMateriPelatihanAction(){
        labelMateriPelatihan.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelMateriPelatihan.setStyle("-fx-underline"));
        labelMateriPelatihan.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelMateriPelatihan.setStyle(""));
    }
    public void handleKalenderInteraktifAction(){
        labelKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelKalenderInteraktif.setStyle("-fx-underline"));
        labelKalenderInteraktif.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelKalenderInteraktif.setStyle(""));
    }
    public void handleChatboxAction(){
        labelChatBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> labelChatBox.setStyle("-fx-underline"));
        labelChatBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> labelChatBox.setStyle(""));
    }
    
    public void handleKeluarAction() {
            new Thread(() -> {
                try {
                    Thread.sleep(200); // Delay for 0.2 seconds
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
    public void handleKeluarActionHover(){
        rectangleKeluar.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> rectangleKeluar.setStyle("-fx-background-color: #ff8d7b"));
        rectangleKeluar.addEventHandler(MouseEvent.MOUSE_EXITED, event -> rectangleKeluar.setStyle("-fx-background-color:  #ff2200"));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> tombolKeluar.setStyle("-fx-background-color:#ff8d7b "));
        tombolKeluar.addEventHandler(MouseEvent.MOUSE_EXITED, event -> tombolKeluar.setStyle("-fx-background-color: #ff2200"));

    }   
            
        
       
    

    // Method to handle 'Mulai dari sini!' button click
    public void handleMateriDasar1Action() {
        // Implement action for starting from here
        System.out.println("Mulai dari sini button clicked!");
    }

      // Add other methods to handle different actions
}
