package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class JogoManualController extends ManualController {
    int flagDica;
    private Stage stage;
    private Scene scene;
    private Parent root;
    boolean flagJogou = false;
    int flagGrid = 0;
    private int linIndexSel, colIndexSel, linIndexDes, colIndexDes;
    @FXML
    private GridPane GPTabuleiro;
    @FXML
    private Label lbDestino;
    @FXML
    private Label lbSelecionada;
    @FXML
    private Button btDica;

    @FXML
    private Button btSair;


    private int linSelecionada, colSelecionada, linJogada, colJogada;

    public void initialize() throws FileNotFoundException {
        jogo.getTabuleiro().tabInicial = jogo.getTabuleiro();
        int i, j;

        for (i = 0; i < 5; i++)
            for (j = 0; j < 5; j++)
                jogo.getTabuleiro().addImagem(GPTabuleiro, i, j, jogo.getTabuleiro().getNomePosicao(i, j), jogo.getTabuleiro().getCorPosicao(i, j), jogo.getTabuleiro().getEscondidaPosicao(i, j));
    }

    @FXML
    void onDicaButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (jogo.getquantDica() < 2) {
            flagDica = 1;
            jogo.setquantDica(jogo.getquantDica() + flagDica);
            alert.setTitle("DICA");
            alert.setHeaderText("Selecione um local.");
            alert.showAndWait();
        } else {
            flagDica = 0;
            alert.setTitle("DICA");
            alert.setHeaderText("Você não possui mais dicas!");
            alert.showAndWait();
        }
    }

    @FXML
    void onSairButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Deseja sair?");
        alert.setContentText("Ao sair, você perderá seu progresso.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            alert.close();
        }
    }
    @FXML
    void onGridClick(MouseEvent event) throws IOException {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (flagDica == 1) {
            int colIndexD;
            colIndexD = jogo.getTabuleiro().getColumnByClickedNode(GPTabuleiro, clickedNode);
            if (jogo.BombaCol(colIndexD)) {
                alert.setHeaderText("Há bombas nessa coluna!");
            } else
                alert.setHeaderText("Não há bombas nessa coluna!");
            flagDica = 0;
            alert.showAndWait();}
        else{
            if (flagGrid == 0) {
                linIndexSel = jogo.getTabuleiro().getRowByClickedNode(GPTabuleiro, clickedNode);
                colIndexSel = jogo.getTabuleiro().getColumnByClickedNode(GPTabuleiro, clickedNode);
                if(jogo.PecaSelecionadaPlayer(linIndexSel, colIndexSel)){
                    lbDestino.setText("");
                    lbSelecionada.setText("Peça selecionada!");
                    flagGrid = 1;}}
            else {
                linIndexDes = jogo.getTabuleiro().getRowByClickedNode(GPTabuleiro, clickedNode);
                colIndexDes = jogo.getTabuleiro().getColumnByClickedNode(GPTabuleiro, clickedNode);
                if (jogo.JogadaPlayer(linIndexSel, colIndexSel, linIndexDes, colIndexDes)) {
                    flagGrid = 0;
                    jogo.AtaquePlayer(linIndexSel, colIndexSel, linIndexDes, colIndexDes);
                    flagJogou = true;
                    lbDestino.setText("Destino selecionado!");}}
                jogo.getTabuleiro().atualizaTabuleiro(GPTabuleiro);
                if (flagJogou == true) {
                    flagJogou = false;
                    jogo.ataqueCom();
                    jogo.getTabuleiro().imprimeTabuleiro();
                }
                jogo.getTabuleiro().atualizaTabuleiro(GPTabuleiro);
            }
        jogo.gameoverPlayer();
        jogo.winPlayer();
        if(jogo.isWin() || jogo.isGameover()){
            root = FXMLLoader.load(getClass().getResource("FinalSceneManual.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 960, 540);
            stage.setScene(scene);
            stage.show();}
    }
}
