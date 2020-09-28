package sample;

import Figures.Triangle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage myStage;
    private Scene scene;
    private AnchorPane pane;


    @Override
    public void start(Stage myStage) throws Exception{
        this.myStage = myStage;
        myStage.setTitle("Фигуры");
        try{
            pane = (AnchorPane) FXMLLoader.load(getClass().getResource("proba1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(pane, 1280, 800);
        myStage.setScene(scene);
        myStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
