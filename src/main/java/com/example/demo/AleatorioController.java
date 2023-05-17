package com.example.demo;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import com.example.demo.modelos.Jogo;
import com.example.demo.modelos.Tabuleiro;
import com.example.demo.modelos.Posicao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.io.IOException;


public class AleatorioController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btDebug;

    @FXML
    private Button btJogar;

    @FXML
    private GridPane GPTabuleiro;

    static Jogo jogo;


    public void initialize() throws FileNotFoundException {
        int i, j;
        jogo = new Jogo();
        jogo.JogoAleatorio();

        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                jogo.getTabuleiro().addImagem(GPTabuleiro, i, j, jogo.getTabuleiro().getNomePosicao(i, j), jogo.getTabuleiro().getCorPosicao(i, j), jogo.getTabuleiro().getEscondidaPosicao(i, j));}
        }}

    @FXML
    void onVoltarButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();}

    @FXML
    void onJogarButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("JogoAleatorioScene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("COMBATE!");
            stage.setScene(new Scene(fxmlLoader.load(), 960, 540));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();}}
    @FXML
    public void onDebugButtonClick(ActionEvent actionEvent) throws FileNotFoundException {
        GPTabuleiro.setGridLinesVisible( false );
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if(!jogo.getTabuleiro().isNullPosicao(new Posicao(i,j)))
                    jogo.getTabuleiro().getPecaPosicao((new Posicao(i,j))).setEscondida(!(jogo.getTabuleiro().getEscondidaPosicao(i, j)));
                GPTabuleiro.getChildren().remove(jogo.getTabuleiro().getNodeByRowColumnIndex(GPTabuleiro, j, i));
                jogo.getTabuleiro().addImagem(GPTabuleiro, i, j, jogo.getTabuleiro().getNomePosicao(i, j), jogo.getTabuleiro().getCorPosicao(i, j), jogo.getTabuleiro().getEscondidaPosicao(i, j));}
        }
        GPTabuleiro.setGridLinesVisible( true );}
}

