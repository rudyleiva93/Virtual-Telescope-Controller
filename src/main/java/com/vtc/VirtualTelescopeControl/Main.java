package com.vtc.VirtualTelescopeControl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("C:/dev/Virtual-Telescope-Controller/src/main/resources/main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/main.fxml"));
        primaryStage.setTitle("Virtual Telescope Controller");
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images/celestron_icon.jpg")));
        Scene main = new Scene(root);
        primaryStage.setScene(main);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
