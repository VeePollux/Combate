package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.demo.modelos.Jogo;
import com.example.demo.modelos.Posicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Optional;


public class FinalSceneMController extends AleatorioController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label lbPrincipal;
    @FXML
    private Label lbSecundaria;
    @FXML
    private Label lbNovoJogo;

    @FXML
    private Button btRestart;

    @FXML
    private Button btSair;
    public void initialize() throws FileNotFoundException {
        lbPrincipal.setText(" ");
        lbSecundaria.setText("");
        if (jogo.isWin()) {
            lbPrincipal.setText("Parabéns!");
            lbSecundaria.setText("Você venceu o jogo!");
        }
        if (jogo.isGameover()) {
            lbPrincipal.setText("Que pena!");
            lbSecundaria.setText("Você perdeu o jogo!");
        }

    }

    @FXML
    public void onRestartButtonClick(ActionEvent event) throws IOException {
        jogo.setTabuleiro(jogo.getTabuleiro().tabInicial);
        root = FXMLLoader.load(getClass().getResource("JogoManualController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();}
    @FXML
    void onSairButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Deseja sair?");
        alert.setContentText("Saindo do programa perderá seu progresso.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            alert.close();
        }
    }
    @FXML
    public void onNovoJogoButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();}
}