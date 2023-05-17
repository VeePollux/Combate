package com.example.demo;
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
public class ManualController {
Image image;
private Stage stage;
private Scene scene;
private Parent root;
private String nome;

@FXML
private Button btDebug;
@FXML
private Button btJogar;
@FXML
private Button btVoltar;
@FXML
private GridPane GPTabuleiro;
@FXML
private ImageView imgBandeira;
@FXML
private ImageView imgBomba;
@FXML
private ImageView imgArmeiro;
@FXML
private ImageView imgEspiao;
@FXML
private ImageView imgMarechal;
@FXML
private ImageView imgSoldado;
static Jogo jogo;

public void initialize() throws FileNotFoundException {
        jogo = new Jogo();
        jogo.CriaTabuleiro();
        jogo.addPecasComp();
        jogo.addLagoAleat();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                jogo.getTabuleiro().addImagem(GPTabuleiro, i, j, jogo.getTabuleiro().getNomePosicao(i, j), jogo.getTabuleiro().getCorPosicao(i, j), jogo.getTabuleiro().getEscondidaPosicao(i, j));}}
    }

    @FXML
    void onVoltarButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();}
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

    @FXML
    void onGridClick(MouseEvent event) throws IOException {
        int colIndex, linIndex;
        Node clickedNode = event.getPickResult().getIntersectedNode();
        colIndex= jogo.getTabuleiro().getColumnByClickedNode(GPTabuleiro, clickedNode);
        linIndex= jogo.getTabuleiro().getRowByClickedNode(GPTabuleiro, clickedNode);
        jogo.addPecasPlayerManual(GPTabuleiro, colIndex, linIndex, nome);}
    @FXML
    void onJogarButtonClick(ActionEvent event) {
        int quantPecas = 0;
        for(int i = 0; i < jogo.getTabuleiro().ListIniciais.size(); i++)
            if(jogo.getTabuleiro().ListIniciais.get(i).getColor().equals("Vermelho"))
                quantPecas++;
        if(quantPecas == 10){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("JogoManualScene.fxml"));
                Stage stage = new Stage();
                stage.setTitle("COMBATE");
                stage.setScene(new Scene(fxmlLoader.load(), 960, 540));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide(); //Esconde stage anterior
            } catch (IOException e) {
                e.printStackTrace();
            }}}

    @FXML
    public void onArmeiroClick(MouseEvent mouseEvent) {
        nome = "CA";
    }
    @FXML

    public void onBandeiraClick(MouseEvent mouseEvent) {
        nome = "BA";
    }
    @FXML
    public void onBombaClick(MouseEvent mouseEvent) {
        nome = "BO";
    }
    @FXML
    public void onEspiaoClick(MouseEvent mouseEvent) {
        nome = "ES";
    }
    @FXML
    public void onSoldadoClick(MouseEvent mouseEvent) {
        nome = "SO";
    }
    @FXML
    public void onMarechalClick(MouseEvent mouseEvent) {
        nome = "MA";
    }
}
