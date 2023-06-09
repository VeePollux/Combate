package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;




public class InicioController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private VBox bt;

    @FXML
    private Button btAleatorio;

    @FXML
    private Button btManual;

    @FXML
    private Label lbTitle;

    @FXML
    public GridPane GPTabuleiro;

    @FXML
    void onAleatorioButtonClick(ActionEvent event)  throws IOException {
        root = FXMLLoader.load(getClass().getResource("AleatorioScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onManualButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManualScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private ImageView IMGInicio;
}

