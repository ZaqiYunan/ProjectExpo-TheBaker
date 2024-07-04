package com.warkop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage loginStage;
    private static Stage registrasiStage;

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.loginStage = primaryStage;
        scene = new Scene(loadFXML("LoginPage"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Warkop - LoginPage");
        primaryStage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        loginStage.setScene(new Scene(loadFXML(fxml)));
        scene.setRoot(loadFXML(fxml));
    }
    static void setTitle(String title) {
        loginStage.setTitle(title);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}