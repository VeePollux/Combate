/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modelos;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Tabuleiro {
    Pecas tabuleiro[][];

    public List<Pecas> ListIniciais;
    public Tabuleiro tabInicial;

    public Tabuleiro() {
        this.tabuleiro = new Pecas[5][5];
        this.ListIniciais = new ArrayList<>();
        this.limparTabuleiro();
    }

    public void limparTabuleiro() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                this.tabuleiro[i][j] = null;
    }

    public void setPosicao(Pecas peca, Posicao pos) {
        this.tabuleiro[pos.getX()][pos.getY()] = peca;
    }

    public void setNullPosicao(Posicao pos) {
        this.tabuleiro[pos.getX()][pos.getY()] = null;
    }

    public boolean isNullPosicao(Posicao pos) {
        return this.tabuleiro[pos.getX()][pos.getY()] == null;
    }

    public Pecas getPecaPosicao(Posicao pos) {
        return this.tabuleiro[pos.getX()][pos.getY()];
    }

    public Pecas getPecaInicial(int x) {
        return this.ListIniciais.get(x);
    }

    public void moverPeca(Posicao origem, Posicao destino) {
        Pecas peca = this.getPecaPosicao(origem);
        this.setPosicao(peca, destino);
        peca.setPosition(destino);
        this.setNullPosicao(origem);
    }

    public Posicao geraPosPecaAleatTab(Tabuleiro tabuleiro, String cor) {
        int X, Y;
        Random gerador = new Random();
        do {
            X = gerador.nextInt(5);
            if (cor.equals("Vermelho"))
                Y = gerador.nextInt(2) + 3;
            else
                Y = gerador.nextInt(2);
        } while (!(tabuleiro.isNullPosicao(new Posicao(X, Y))));
        Posicao position = new Posicao(X, Y);
        return position;
    }

    public void imprimeTabuleiro() { //Funcao para testes
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (getPecaPosicao(new Posicao(y, x)) == null)
                    System.out.print("000 ");
                else {
                    if (getPecaPosicao(new Posicao(y, x)).getColor().equals("Azul"))
                        System.out.print(getPecaPosicao(new Posicao(y, x)).getNome() + "1 ");
                    else if (getPecaPosicao(new Posicao(y, x)).getColor().equals("Vermelho"))
                        System.out.print(getPecaPosicao(new Posicao(y, x)).getNome() + "2 "); // Representa o Player
                    else
                        System.out.print(getPecaPosicao(new Posicao(y, x)).getNome() + "  ");
                }
            }
            System.out.print("\n");
        }
    }

    public boolean getEscondidaPosicao(int linha, int coluna) {
        try {
            return getPecaPosicao(new Posicao(linha, coluna)).isEscondida();
        } catch (NullPointerException e) {
            return false;
        }
    }

    public String getNomePosicao(int linha, int coluna) {
        try {
            return getPecaPosicao(new Posicao(linha, coluna)).getNome();
        } catch (NullPointerException e) {
            return "Vazio";
        }
    }

    public String getCorPosicao(int linha, int coluna) {
        try {
            return getPecaPosicao(new Posicao(linha, coluna)).getColor();
        } catch (NullPointerException e) {
            return "Vazio";
        }
    }

    public void addImagem(GridPane GPTabuleiro, int linIndex, int colIndex, String nome, String cor, boolean escondida) throws
            FileNotFoundException { //Verifica qual é a imagem e coloca no tabuleiro
        String imagePath = "/Imagens/BandeiraInicio.png"; //inicializando variavel
        ImageView imageView;

        switch (nome) {
            case "MA":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/MarechalAzul.png";
                else
                    imagePath = "/Imagens/MarechalVermelho.png";
                break;
            case "BO":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/BombaAzul.png";
                else
                    imagePath = "/Imagens/BombaVermelha.png";
                break;
            case "BA":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/BandeiraAzul.png";
                else
                    imagePath = "/Imagens/BandeiraVermelha.png";
                break;
            case "ES":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/EspiaoAzul.png";
                else
                    imagePath = "/Imagens/EspiaoVermelho.png";
                break;
            case "CA":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/ArmeiroAzul.png";
                else
                    imagePath = "/Imagens/ArmeiroVermelho.png";
                break;
            case "SO":
                if (cor.equals("Azul"))
                    imagePath = "/Imagens/SoldadoAzul.png";
                else
                    imagePath = "/Imagens/SoldadoVermelho.png";
                break;
            case "LA":
                imagePath = "/Imagens/lago.png";
                break;
            case "Vazio":
                imagePath = "/Imagens/FundoVerde.png";
                break;
        }
        if (escondida && cor.equals("Azul"))
            imagePath = "/Imagens/Escondido.png";
        URL resource;
        resource = Tabuleiro.class.getResource(imagePath);
        if (!(resource == null)) {
            String path = resource.toExternalForm();
            Image image = new Image(path);
            setImagem(GPTabuleiro, image, linIndex, colIndex);
        }
    }
    public void addFundoVerde(GridPane grTabuleiro) {
        String imagePath = "/Imagens/FundoVerde.png"; //Precisei colocar fundo verde pois o grid estava selecionando apenas
        ImageView imageView;                            //No contorno do png das peças
        URL resource;
        resource = Tabuleiro.class.getResource(imagePath);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (!(resource == null)) {
                    String path = resource.toExternalForm();
                    Image image = new Image(path);
                    setImagem(grTabuleiro, image, i, j);
                }
    }

    public void setImagem(GridPane GPTabuleiro, Image image, int linIndex, int colIndex) { //Coloca a imagem bonitinha
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        GPTabuleiro.add(imageView, linIndex, colIndex);
        GridPane.setHalignment(imageView, HPos.CENTER);
        GridPane.setValignment(imageView, VPos.CENTER);
    }

    public Node getNodeByRowColumnIndex(GridPane GPTabuleiro, final int row, final int column) {
        int linIndex, colIndex;
        Node result = null;
        ObservableList<Node> childrens = GPTabuleiro.getChildren();
        for (Node node : childrens) {
            try {
                colIndex = GPTabuleiro.getColumnIndex(node);
            } catch (NullPointerException e) {  //Para quando ocorre NullPointerException pois o Grid da mt erro nisso
                colIndex = 0;}
            try {
                linIndex = GPTabuleiro.getRowIndex(node);
            } catch (NullPointerException e) {
                linIndex = 0;}
            if (linIndex == row && colIndex == column) {
                result = node;
                break;}}
        return result;
    }

    public int getColumnByClickedNode(GridPane GPTabuleiro, Node clickedNode) {
        int col;
        GPTabuleiro.setGridLinesVisible(false);
        try {
            col = GPTabuleiro.getColumnIndex(clickedNode);
        } catch (NullPointerException e) {
            System.out.println("Entrou1");
            col = 0;
        }
        GPTabuleiro.setGridLinesVisible(true);
        return col;
    }

    public int getRowByClickedNode(GridPane GPTabuleiro, Node clickedNode) {
        int lin;
        GPTabuleiro.setGridLinesVisible(false);
        try {
            lin = GPTabuleiro.getRowIndex(clickedNode);
        } catch (NullPointerException e) {
            lin = 0;
        }
        GPTabuleiro.setGridLinesVisible(true);
        return lin;
    }

    public void atualizaTabuleiro(GridPane GPTabuleiro) throws FileNotFoundException {
        String nome;
        ImageView imageview;
        addFundoVerde(GPTabuleiro);
        for(int i=0; i<5; i++) { //posiciona personagens azuis (jogador)
            for (int j = 0; j < 5; j++) {
                if(!isNullPosicao(new Posicao(i, j))) {
                    GPTabuleiro.getChildren().removeAll(getNodeByRowColumnIndex(GPTabuleiro,i, j));

                    nome = getNomePosicao(i, j);
                    if (getCorPosicao(i, j).equals("Vermelho")) {
                        addImagem(GPTabuleiro, i, j, nome, "Vermelho", false);
                    } else {
                        if (getCorPosicao(i, j).equals("Azul")) {
                            if (getEscondidaPosicao(i, j))
                                addImagem(GPTabuleiro, i, j, nome, "Azul", true);
                            else
                                addImagem(GPTabuleiro, i, j, nome, "Azul", false);
                        } else {
                            if (getCorPosicao(i, j).equals("Indefinido")) {
                                addImagem(GPTabuleiro, i, j, nome, "Indefinido", false);
                            }
                        }
                    }
                }
                if(tabuleiro[i][j]==null){
                    GPTabuleiro.getChildren().removeAll(getNodeByRowColumnIndex(GPTabuleiro,i, j));
                    addImagem(GPTabuleiro, i, j, "Vazio", "Inferente", false);
                }
            }
        }
        GPTabuleiro.setGridLinesVisible( true );
    }
}